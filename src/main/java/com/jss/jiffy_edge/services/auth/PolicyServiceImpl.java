package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.dao.entities.auth.*;
import com.jss.jiffy_edge.dao.repo.auth.*;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.models.auth.PolicyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private AccessPolicyMasterRepository accessPolicyMasterRepository;

//	@Autowired
//	private TblUserRolesRepository tblUserRolesRepository;
//
//	@Autowired
//	private TblUsersRepository tblUsersRepository;

	@Autowired
	private VwAppliedAccessPoliciesRepository vwAppliedAccessPoliciesRepository;

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