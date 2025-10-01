package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUsers;

public class UserUpdateRequest {
    private String name;
    private String email;
    private String password;
    private String mobile;
    private Integer roleId;
    private TblUsers.UserStatus status;

    public TblUsers.UserStatus getStatus() {
        return status;
    }

    public void setStatus(TblUsers.UserStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}