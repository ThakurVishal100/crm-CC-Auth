package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.OwnerType;
import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;

public class RoleRequest {

    private String roleName;
    private TblUsers.UserCategory roleCatg;
    private String roleDesc;
    private OwnerType ownerType;
    private Integer ownerId;
    private Integer defaAccessPolicyId;
    private TblUserRoles.UserStatus status;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public TblUsers.UserCategory getRoleCatg() {
        return roleCatg;
    }

    public void setRoleCatg(TblUsers.UserCategory roleCatg) {
        this.roleCatg = roleCatg;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getDefaAccessPolicyId() {
        return defaAccessPolicyId;
    }

    public void setDefaAccessPolicyId(Integer defaAccessPolicyId) {
        this.defaAccessPolicyId = defaAccessPolicyId;
    }

    public TblUserRoles.UserStatus getStatus() {
        return status;
    }

    public void setStatus(TblUserRoles.UserStatus status) {
        this.status = status;
    }
}