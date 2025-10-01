package com.jss.jiffy_edge.convertors.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.models.auth.RoleRequest;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public TblUserRoles toEntity(RoleRequest request) {
        TblUserRoles entity = new TblUserRoles();
        entity.setRoleName(request.getRoleName());
        entity.setRoleCatg(request.getRoleCatg());
        entity.setRoleDesc(request.getRoleDesc());
        entity.setOwnerType(request.getOwnerType());
        entity.setOwnerId(request.getOwnerId());
        entity.setDefaAccessPolicyId(request.getDefaAccessPolicyId());
        entity.setStatus(request.getStatus());
        return entity;
    }
}