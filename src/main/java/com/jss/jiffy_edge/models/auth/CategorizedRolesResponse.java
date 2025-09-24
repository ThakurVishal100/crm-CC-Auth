package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import java.util.List;

public class CategorizedRolesResponse {

    private List<TblUserRoles> systemRoles;
    private List<TblUserRoles> externalRoles;

    public CategorizedRolesResponse(List<TblUserRoles> systemRoles, List<TblUserRoles> externalRoles) {
        this.systemRoles = systemRoles;
        this.externalRoles = externalRoles;
    }

    public List<TblUserRoles> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(List<TblUserRoles> systemRoles) {
        this.systemRoles = systemRoles;
    }

    public List<TblUserRoles> getExternalRoles() {
        return externalRoles;
    }

    public void setExternalRoles(List<TblUserRoles> externalRoles) {
        this.externalRoles = externalRoles;
    }
}

