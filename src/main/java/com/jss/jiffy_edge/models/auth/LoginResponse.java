package com.jss.jiffy_edge.models.auth;

import java.util.List;

public class LoginResponse {

    private String token;
    private List<PolicyResponse> menus;

    public LoginResponse(String token, List<PolicyResponse> menus) {
        this.token = token;
        this.menus = menus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<PolicyResponse> getMenus() {
        return menus;
    }

    public void setMenus(List<PolicyResponse> menus) {
        this.menus = menus;
    }
}