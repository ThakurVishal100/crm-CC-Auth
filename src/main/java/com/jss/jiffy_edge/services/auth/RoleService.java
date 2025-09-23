package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.models.auth.RoleRequest;

import java.util.List;

public interface RoleService {
    TblUserRoles createRole(RoleRequest roleRequest);
    TblUserRoles updateRole(Integer roleId, RoleRequest roleRequest);
    void deleteRole(Integer roleId);
    List<TblUserRoles> getAllRoles();
}
