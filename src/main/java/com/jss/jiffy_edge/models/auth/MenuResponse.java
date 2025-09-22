package com.jss.jiffy_edge.models.auth;

public class MenuResponse {
    private String menuName;

    public MenuResponse(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}