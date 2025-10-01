package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;

public class AddonPolicyRequest {

    private String accessDescp;
    private Integer roleId;
    private Integer userId;
    private TblAccessAddonMap.MappingLevel mappingLevel;
    private Integer masterPolicyId;
    private TblAccessAddonMap.Status status;

    // Getters and Setters

    public String getAccessDescp() {
        return accessDescp;
    }

    public void setAccessDescp(String accessDescp) {
        this.accessDescp = accessDescp;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TblAccessAddonMap.MappingLevel getMappingLevel() {
        return mappingLevel;
    }

    public void setMappingLevel(TblAccessAddonMap.MappingLevel mappingLevel) {
        this.mappingLevel = mappingLevel;
    }

    public Integer getMasterPolicyId() {
        return masterPolicyId;
    }

    public void setMasterPolicyId(Integer masterPolicyId) {
        this.masterPolicyId = masterPolicyId;
    }

    public TblAccessAddonMap.Status getStatus() {
        return status;
    }

    public void setStatus(TblAccessAddonMap.Status status) {
        this.status = status;
    }
}