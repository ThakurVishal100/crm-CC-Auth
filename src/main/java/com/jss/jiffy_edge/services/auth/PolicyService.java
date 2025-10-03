package com.jss.jiffy_edge.services.auth;

import java.nio.file.AccessDeniedException;
import java.util.List;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails;
import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyMaster;
import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.dao.entities.auth.SysAccessLevel;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.models.auth.*;

public interface PolicyService {
    AccessPolicyMaster createMasterPolicy(MasterPolicyRequest request);

    AccessPolicyMaster updateMasterPolicy(Integer policyId, MasterPolicyRequest request);

    void deleteMasterPolicy(Integer policyId);

    List<AccessPolicyMaster> getAllMasterPolicies(Integer requesterRoleId);

    List<PolicyResponse> getPoliciesByRoleId(Integer roleId);

    List<PolicyResponse> getAppliedPoliciesByRoleId(Integer roleId);

    List<PermissionGrantResponse> getPermissionsForGrantPage();

    void grantPermissionToRole(Integer requesterRoleId, GrantPermissionRequest request) throws AccessDeniedException;


    List<PolicyResponse> getPoliciesByUserId(Integer userId);

    List<ServiceDetails> getAllServices();

    List<SysAccessLevel> getAllSysAccessLevels();

    List<AccessPolicyDetails> getAllAccessPolicies();

    AccessPolicyDetails createAccessPolicy(SystemAccessPolicyRequest request);

    AccessPolicyDetails deleteAccessPolicy(Long policyId);

    void updateUserPolicy(Integer requesterId, UpdateUserPolicyRequest request) throws AccessDeniedException;


    AccessPolicyDetails updateAccessPolicy(Long policyId, SystemAccessPolicyRequest request);
}