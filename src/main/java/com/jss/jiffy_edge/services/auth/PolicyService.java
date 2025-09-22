package com.jss.jiffy_edge.services.auth;

import java.util.List;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails;
import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.dao.entities.auth.SysAccessLevel;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.models.auth.PolicyResponse;

public interface PolicyService {
    List<PolicyResponse> getPoliciesByRoleId(Integer roleId);

    List<PolicyResponse> getAppliedPoliciesByRoleId(Integer roleId);

    List<PolicyResponse> getPoliciesByUserId(Integer userId);

    List<ServiceDetails> getAllServices();

    List<SysAccessLevel> getAllSysAccessLevels();

    List<AccessPolicyDetails> getAllAccessPolicies();

    AccessPolicyDetails createAccessPolicy(SystemAccessPolicyRequest request);

    void deleteAccessPolicy(Long policyId);

    AccessPolicyDetails updateAccessPolicy(Long policyId, SystemAccessPolicyRequest request);
}