package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.dao.entities.auth.TblSystemMenu;

import java.util.List;

public class PermissionGrantResponse {

    private ServiceDetails service;
    private List<TblSystemMenu> menus;

    public PermissionGrantResponse(ServiceDetails service, List<TblSystemMenu> menus) {
        this.service = service;
        this.menus = menus;
    }


    public ServiceDetails getService() {
        return service;
    }

    public void setService(ServiceDetails service) {
        this.service = service;
    }

    public List<TblSystemMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<TblSystemMenu> menus) {
        this.menus = menus;
    }
}