package com.jss.jiffy_edge.models.auth;

import java.util.List;

public class CategorizedUsersResponse {

    private List<UserResponse> systemUsers;
    private List<UserResponse> externalUsers;

    public CategorizedUsersResponse(List<UserResponse> systemUsers, List<UserResponse> externalUsers) {
        this.systemUsers = systemUsers;
        this.externalUsers = externalUsers;
    }

    public List<UserResponse> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(List<UserResponse> systemUsers) {
        this.systemUsers = systemUsers;
    }

    public List<UserResponse> getExternalUsers() {
        return externalUsers;
    }

    public void setExternalUsers(List<UserResponse> externalUsers) {
        this.externalUsers = externalUsers;
    }
}

