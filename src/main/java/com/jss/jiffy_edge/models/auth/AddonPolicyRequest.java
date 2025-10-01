package com.jss.jiffy_edge.models.auth;


public class AddonPolicyRequest {

    private Integer roleId;
    private Long policyId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }
}
