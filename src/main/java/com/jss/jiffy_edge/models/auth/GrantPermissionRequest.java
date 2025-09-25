package com.jss.jiffy_edge.models.auth;

public class GrantPermissionRequest {

    private Integer roleId;
    private Long policyId;
    private String accessLevel; // e.g., "READ", "WRITE", "DELETE"

    // Getters and Setters
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

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}