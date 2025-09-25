package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.dao.entities.auth.*;
import com.jss.jiffy_edge.dao.repo.auth.*;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.models.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

	private static final Integer SUPER_USER_ROLE_ID = 1;
	private static final Integer ROOT_USER_ROLE_ID = 2;


	@Override
	public AccessPolicyMaster createMasterPolicy(MasterPolicyRequest request) {
		AccessPolicyMaster masterPolicy = new AccessPolicyMaster();
		masterPolicy.setPolicyName(request.getPolicyName());
		masterPolicy.setDescp(request.getDescp());
		masterPolicy.setStatus(request.getStatus());
		masterPolicy.setCategory(request.getCategory());
		masterPolicy.setAvlForSuonly(request.getAvlForSuonly());
		return accessPolicyMasterRepository.save(masterPolicy);
	}

	@Override
	public List<PermissionGrantResponse> getPermissionsForGrantPage() {
		List<ServiceDetails> services = serviceDetailsRepository.findAll();
		return services.stream()
				.map(service -> {
					List<TblSystemMenu> menus = tblSystemMenuRepository.findByServiceId(service.getServiceId());
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

		// Security Check: Prevent users from editing roles above them
		if (requesterRoleId >= targetRole.getRoleId()) {
			throw new AccessDeniedException("You are not authorized to modify permissions for this role.");
		}

		// Security Check: Ensure only system users can manage the Root User's ACL
		if (targetRole.getRoleId().equals(ROOT_USER_ROLE_ID)) {
			if (requesterRole.getRoleCatg() != TblUsers.UserCategory.SYSTEM_USER) {
				throw new AccessDeniedException("You are not authorized to modify the Root User's permissions.");
			}
		}

		// --- Placeholder for Permission Granting Logic ---
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
			// System users can see all policies except those for the super user
			return accessPolicyMasterRepository.findByAvlForSuonlyNot(1);
		} else {
			// External users can only see external policies
			return accessPolicyMasterRepository.findByCategory(AccessPolicyMaster.Category.EXTERNAL);
		}
	}

	@Override
	public void updateUserPolicy(Integer requesterId, UpdateUserPolicyRequest request) throws AccessDeniedException {
		TblUsers requester = tblUsersRepository.findById(requesterId)
				.orElseThrow(() -> new RuntimeException("Requester not found."));

		// Allow users to modify their own policies
		if (requesterId.equals(request.getTargetUserId())) {
			// Logic to update the user's own role/policy
			TblUsers targetUser = tblUsersRepository.findById(request.getTargetUserId()).orElseThrow(() -> new RuntimeException("User not found"));
			TblUserRoles newRole = tblUserRolesRepository.findById(request.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
			targetUser.setRole(newRole);
			tblUsersRepository.save(targetUser);
			return;
		}

		// If the user is external, check if they are managing the target user
		if (requester.getUserCatg() == TblUsers.UserCategory.EXTERNAL_USER) {
			boolean isManager = userRelationshipRepository.existsByManager_UserIdAndSubordinate_UserId(requesterId, request.getTargetUserId());
			if (!isManager) {
				throw new AccessDeniedException("You are not authorized to modify this user's policy.");
			}
		}

		// Logic to update the user's role/policy
		TblUsers targetUser = tblUsersRepository.findById(request.getTargetUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		TblUserRoles newRole = tblUserRolesRepository.findById(request.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
		targetUser.setRole(newRole);
		tblUsersRepository.save(targetUser);
	}


	@Override
	public List<PolicyResponse> getPoliciesByRoleId(Integer roleId) {
		return Collections.emptyList();
	}

	@Override
	public List<PolicyResponse> getAppliedPoliciesByRoleId(Integer roleId) {
		return vwAppliedAccessPoliciesRepository.findByRoleId(roleId).stream().map(this::mapToPolicyResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<PolicyResponse> getPoliciesByUserId(Integer userId) {

		return Collections.emptyList();
	}

	@Override
	public List<ServiceDetails> getAllServices() {
		return serviceDetailsRepository.findAll();
	}

	@Override
	public List<SysAccessLevel> getAllSysAccessLevels() {
		return sysAccessLevelRepository.findAll();
	}

	@Override
	public List<AccessPolicyDetails> getAllAccessPolicies() {
		return accessPolicyDetailsRepository.findAll();
	}

	@Override
	public AccessPolicyDetails createAccessPolicy(SystemAccessPolicyRequest request) {
		AccessPolicyDetails policy = new AccessPolicyDetails();
		return updatePolicyFromRequest(policy, request);
	}

	@Override
	public void deleteAccessPolicy(Long policyId) {
		accessPolicyDetailsRepository.deleteById(policyId);
	}

	@Override
	public AccessPolicyDetails updateAccessPolicy(Long policyId, SystemAccessPolicyRequest request) {
		AccessPolicyDetails policy = accessPolicyDetailsRepository.findById(policyId)
				.orElseThrow(() -> new RuntimeException("Policy not found with id: " + policyId));
		return updatePolicyFromRequest(policy, request);
	}

	private AccessPolicyDetails updatePolicyFromRequest(AccessPolicyDetails policy, SystemAccessPolicyRequest request) {
		policy.setAccessName(request.getAccessName());
		policy.setAccessDesc(request.getAccessDesc());

		AccessPolicyMaster apm = accessPolicyMasterRepository.findById(request.getMasterPolicyId())
				.orElseThrow(() -> new RuntimeException("AccessPolicyMaster not found"));
		policy.setAccessPolicyMaster(apm);

		ServiceDetails sd = serviceDetailsRepository.findById(request.getServiceId())
				.orElseThrow(() -> new RuntimeException("ServiceDetails not found"));
		policy.setServiceDetails(sd);

		SysAccessLevel sal = sysAccessLevelRepository.findById(request.getSysAccessLvlId())
				.orElseThrow(() -> new RuntimeException("SysAccessLevel not found"));
		policy.setSysAccessLevel(sal);

		policy.setStatus(request.getStatus());

		policy.setCreationDate(LocalDateTime.now());
		policy.setLastUpdate(LocalDateTime.now());
		return accessPolicyDetailsRepository.save(policy);
	}

	private PolicyResponse mapToPolicyResponse(VwAppliedAccessPolicies policy) {
		ServiceDetails serviceDetails = new ServiceDetails();
		serviceDetails.setServiceId(policy.getServiceId());
		serviceDetails.setServiceName(policy.getServiceName());
		serviceDetails.setServiceDescp(policy.getServiceDescp());
		serviceDetails.setServiceCatg(policy.getServiceCatg());
		serviceDetails.setParentServiceId(policy.getParentServiceId());
		serviceDetails.setStatus(policy.getServiceStatus());

		PolicyResponse response = new PolicyResponse();
		response.setPolicyId(policy.getPolicyId());
		response.setPolicyName(policy.getAccessName());
		response.setPolicyDescription(policy.getAccessDesc());
		response.setServiceDetails(serviceDetails);
		response.setPermissions(getPermissionsFromSysAccessLevel(policy.getSysAccessLvlname()));
		response.setMenuId(policy.getMenuId());
		response.setMenuName(policy.getMenuName());
		response.setMenuIcon(policy.getMenuIcon());
		response.setMenuNameToDisplay(policy.getMenuNameToDisplay());
		response.setHintText(policy.getHintText());
		response.setMenuDescp(policy.getMenuDescp());
		response.setMenuLevel(policy.getMenuLevel());
		response.setHasChild(policy.isHasChild());
		response.setChildMenu(policy.isChildMenu());
		response.setShowOrder(policy.getShowOrder());
		response.setTargetLink(policy.getTargetLink());
		response.setTargetType(policy.getTargetType());
		response.setMenuStatus(policy.getMenuStatus());
		response.setSuAccessOnly(policy.getSuAccessOnly());

		return response;
	}

	private List<String> getPermissionsFromSysAccessLevel(SysAccessLevel.SysAccessLvlName lvlName) {
		if (lvlName == null) {
			return Collections.emptyList();
		}
		List<String> permissions = new ArrayList<>();
		switch (lvlName) {
			case ALL_ACCESS:
			case SYSTEM_ADMIN:
			case INSTANCE_ADMIN:
			case SERVICE_OWNER:
			case READ_WRITE_DELETE_CREATE:
				permissions.add("CREATE");
			case READ_WRITE_DELETE:
				permissions.add("DELETE");
			case READ_WRITE:
				permissions.add("WRITE");
			case READ:
				permissions.add("READ");
				break;
			default:
				break;
		}
		return permissions;
	}
}