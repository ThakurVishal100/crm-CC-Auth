package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.convertors.auth.PolicyConverter;
import com.jss.jiffy_edge.dao.entities.auth.*;
import com.jss.jiffy_edge.dao.repo.auth.*;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.models.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private AccessPolicyDetailsRepository accessPolicyDetailsRepository;

	@Autowired
	private ServiceDetailsRepository serviceDetailsRepository;


	@Autowired
	private SysAccessLevelRepository sysAccessLevelRepository;

	@Autowired
	private TblSystemMenuRepository tblSystemMenuRepository;


	@Autowired
	private AccessPolicyMasterRepository accessPolicyMasterRepository;

	@Autowired
	private TblUsersRepository tblUsersRepository;

	@Autowired
	private TblUserRelationshipRepository userRelationshipRepository;


	@Autowired
	private TblUserRolesRepository tblUserRolesRepository;

	@Autowired
	private VwAppliedAccessPoliciesRepository vwAppliedAccessPoliciesRepository;

	@Autowired
	private PolicyConverter policyConverter;


	private static final Integer SUPER_USER_ROLE_ID = 1;
	private static final Integer ROOT_USER_ROLE_ID = 2;


	@Override
	public AccessPolicyMaster createMasterPolicy(MasterPolicyRequest request) {
		AccessPolicyMaster masterPolicy = policyConverter.toMasterEntity(request);
		return accessPolicyMasterRepository.save(masterPolicy);
	}

	@Override
	public AccessPolicyMaster updateMasterPolicy(Integer policyId, MasterPolicyRequest request) {
		AccessPolicyMaster masterPolicy = accessPolicyMasterRepository.findById(policyId)
				.orElseThrow(() -> new RuntimeException("Master policy not found with id: " + policyId));

		policyConverter.updateMasterEntityFromRequest(masterPolicy, request);
		return accessPolicyMasterRepository.save(masterPolicy);
	}

	@Override
	public void deleteMasterPolicy(Integer policyId) {
		AccessPolicyMaster masterPolicy = accessPolicyMasterRepository.findById(policyId)
				.orElseThrow(() -> new RuntimeException("Master policy not found with id: " + policyId));
		masterPolicy.setStatus(AccessPolicyMaster.Status.INACTIVE);
		accessPolicyMasterRepository.save(masterPolicy);
	}

	@Override
	public List<PermissionGrantResponse> getPermissionsForGrantPage() {
		List<ServiceDetails> services = serviceDetailsRepository.findByStatus(ServiceDetails.Status.ACTIVE);
		return services.stream()
				.map(service -> {
					List<TblSystemMenu> menus = tblSystemMenuRepository.findByServiceIdAndStatus(service.getServiceId(), TblSystemMenu.Status.ACTIVE);
					return new PermissionGrantResponse(service, menus);
				})
				.collect(Collectors.toList());
	}

	@Override
	public void grantPermissionToRole(Integer requesterRoleId, GrantPermissionRequest request) throws AccessDeniedException {
		TblUserRoles requesterRole = tblUserRolesRepository.findById(requesterRoleId)
				.orElseThrow(() -> new RuntimeException("Requester role not found with id: " + requesterRoleId));

		TblUserRoles targetRole = tblUserRolesRepository.findById(request.getRoleId())
				.orElseThrow(() -> new RuntimeException("Target role not found with id: " + request.getRoleId()));

		if (requesterRoleId >= targetRole.getRoleId()) {
			throw new AccessDeniedException("You are not authorized to modify permissions for this role.");
		}

		if (targetRole.getRoleId().equals(ROOT_USER_ROLE_ID)) {
			if (requesterRole.getRoleCatg() != TblUsers.UserCategory.SYSTEM_USER) {
				throw new AccessDeniedException("You are not authorized to modify the Root User's permissions.");
			}
		}

		System.out.println("User with role " + requesterRoleId + " is granting permission for policy " + request.getPolicyId() + " to role " + request.getRoleId());
	}



	@Override
	public List<AccessPolicyMaster> getAllMasterPolicies(Integer requesterRoleId) {
		TblUserRoles requesterRole = tblUserRolesRepository.findById(requesterRoleId)
				.orElseThrow(() -> new RuntimeException("Requesting user's role not found."));


		if (SUPER_USER_ROLE_ID.equals(requesterRoleId)) {
			return accessPolicyMasterRepository.findAll();
		}

		if (requesterRole.getRoleCatg() == TblUsers.UserCategory.SYSTEM_USER) {
			return accessPolicyMasterRepository.findByAvlForSuonlyNot(1);
		} else {
			return accessPolicyMasterRepository.findByCategory(AccessPolicyMaster.Category.EXTERNAL);
		}
	}

	@Override
	public void updateUserPolicy(Integer requesterId, UpdateUserPolicyRequest request) throws AccessDeniedException {
		TblUsers requester = tblUsersRepository.findById(requesterId)
				.orElseThrow(() -> new RuntimeException("Requester not found."));

		if (requesterId.equals(request.getTargetUserId())) {
			TblUsers targetUser = tblUsersRepository.findById(request.getTargetUserId()).orElseThrow(() -> new RuntimeException("User not found"));
			TblUserRoles newRole = tblUserRolesRepository.findById(request.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
			targetUser.setRole(newRole);
			tblUsersRepository.save(targetUser);
			return;
		}

		if (requester.getUserCatg() == TblUsers.UserCategory.EXTERNAL_USER) {
			boolean isManager = userRelationshipRepository.existsByManager_UserIdAndSubordinate_UserId(requesterId, request.getTargetUserId());
			if (!isManager) {
				throw new AccessDeniedException("You are not authorized to modify this user's policy.");
			}
		}

		TblUsers targetUser = tblUsersRepository.findById(request.getTargetUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		TblUserRoles newRole = tblUserRolesRepository.findById(request.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
		targetUser.setRole(newRole);
		tblUsersRepository.save(targetUser);
	}


	@Override
	public List<PolicyResponse> getPoliciesByRoleId(Integer roleId) {
		return vwAppliedAccessPoliciesRepository.findByRoleId(roleId).stream()
				.map(policyConverter::toPolicyResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<PolicyResponse> getAppliedPoliciesByRoleId(Integer roleId) {
		return vwAppliedAccessPoliciesRepository.findByRoleId(roleId).stream().map(policyConverter::toPolicyResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<PolicyResponse> getPoliciesByUserId(Integer userId) {
		TblUsers user = tblUsersRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

		Integer roleId = user.getRole().getRoleId();

		return getAppliedPoliciesByRoleId(roleId);
	}
	@Override
	public List<ServiceDetails> getAllServices() {
		return serviceDetailsRepository.findByStatus(ServiceDetails.Status.ACTIVE);
	}

	@Override
	public List<SysAccessLevel> getAllSysAccessLevels() {
		return sysAccessLevelRepository.findAll();
	}

	@Override
	public List<AccessPolicyDetails> getAllAccessPolicies() {
		return accessPolicyDetailsRepository.findByStatus(AccessPolicyDetails.Status.ACTIVE);
	}

	@Override
	public AccessPolicyDetails createAccessPolicy(SystemAccessPolicyRequest request) {
		AccessPolicyDetails policy = policyConverter.toDetailsEntity(request);
		return updatePolicyFromRequest(policy, request);
	}

	@Override
	public AccessPolicyDetails deleteAccessPolicy(Long policyId) {
		AccessPolicyDetails policy = accessPolicyDetailsRepository.findById(policyId)
				.orElseThrow(() -> new RuntimeException("Policy not found with id: " + policyId));
		policy.setStatus(AccessPolicyDetails.Status.INACTIVE);
		return accessPolicyDetailsRepository.save(policy);
	}

	@Override
	public AccessPolicyDetails updateAccessPolicy(Long policyId, SystemAccessPolicyRequest request) {
		AccessPolicyDetails policy = accessPolicyDetailsRepository.findById(policyId)
				.orElseThrow(() -> new RuntimeException("Policy not found with id: " + policyId));
		policy.setAccessName(request.getAccessName());
		policy.setAccessDesc(request.getAccessDesc());
		policy.setStatus(request.getStatus());
		return updatePolicyFromRequest(policy, request);
	}

	private AccessPolicyDetails updatePolicyFromRequest(AccessPolicyDetails policy, SystemAccessPolicyRequest request) {

		AccessPolicyMaster apm = accessPolicyMasterRepository.findById(request.getMasterPolicyId())
				.orElseThrow(() -> new RuntimeException("AccessPolicyMaster not found"));
		policy.setAccessPolicyMaster(apm);

		ServiceDetails sd = serviceDetailsRepository.findById(request.getServiceId())
				.orElseThrow(() -> new RuntimeException("ServiceDetails not found"));
		policy.setServiceDetails(sd);

		if (request.getMenuId() != null) {
			TblSystemMenu menu = tblSystemMenuRepository.findById(request.getMenuId())
					.orElseThrow(() -> new RuntimeException("Menu not found"));
			policy.setMenu(menu);
		}

		SysAccessLevel sal = sysAccessLevelRepository.findById(request.getSysAccessLvlId())
				.orElseThrow(() -> new RuntimeException("SysAccessLevel not found"));
		policy.setSysAccessLevel(sal);

		policy.setCreationDate(LocalDateTime.now());
		policy.setLastUpdate(LocalDateTime.now());
		return accessPolicyDetailsRepository.save(policy);
	}
}