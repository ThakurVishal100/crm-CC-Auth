package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;
import com.jss.jiffy_edge.models.auth.AddonPolicyRequest;

import java.util.List;

public interface AddonPolicyService {
    List<TblAccessAddonMap> getAllAddonPolicies();
    TblAccessAddonMap createAddonPolicy(AddonPolicyRequest addonPolicyRequest);
    TblAccessAddonMap updateAddonPolicy(Integer addonPolicyId, AddonPolicyRequest addonPolicyRequest);
    void deleteAddonPolicy(Integer addonPolicyId);
    List<TblAccessAddonMap> getAddonPoliciesByRoleId(Integer roleId);
    List<TblAccessAddonMap> getAddonPoliciesByUserId(Integer userId);
}
