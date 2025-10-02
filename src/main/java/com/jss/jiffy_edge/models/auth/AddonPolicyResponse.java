package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;
import java.time.LocalDateTime;

public class AddonPolicyResponse {

    private int addonAccessId;
    private String accessDescp;
    private Integer roleId;
    private String roleName;
    private Integer userId;
    private String userName;
    private TblAccessAddonMap.MappingLevel mappingLevel;
    private Integer masterPolicyId;
    private String masterPolicyName;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private TblAccessAddonMap.Status status;

    // Getters and Setters
    public int getAddonAccessId() {
        return addonAccessId;
    }

    public void setAddonAccessId(int addonAccessId) {
        this.addonAccessId = addonAccessId;
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getMasterPolicyName() {
        return masterPolicyName;
    }

    public void setMasterPolicyName(String masterPolicyName) {
        this.masterPolicyName = masterPolicyName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public TblAccessAddonMap.Status getStatus() {
        return status;
    }

    public void setStatus(TblAccessAddonMap.Status status) {
        this.status = status;
    }
}
