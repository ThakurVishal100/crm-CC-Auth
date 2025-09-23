package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.repo.auth.TblUserRolesRepository;
import com.jss.jiffy_edge.models.auth.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private TblUserRolesRepository roleRepository;

    @Override
    public TblUserRoles createRole(RoleRequest roleRequest) {
        TblUserRoles role = new TblUserRoles();
        role.setCreationDate(new Date());
        return updateRoleFromRequest(role, roleRequest);
    }

    @Override
    public TblUserRoles updateRole(Integer roleId, RoleRequest roleRequest) {
        TblUserRoles role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        return updateRoleFromRequest(role, roleRequest);
    }

    @Override
    public void deleteRole(Integer roleId) {
        TblUserRoles role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        role.setStatus(TblUserRoles.UserStatus.INACTIVE);
        role.setLastUpdate(new Date());
        roleRepository.save(role);
    }

    @Override
    public List<TblUserRoles> getAllRoles() {
        return roleRepository.findAll();
    }

    private TblUserRoles updateRoleFromRequest(TblUserRoles role, RoleRequest request) {
        role.setRoleName(request.getRoleName());
        role.setRoleCatg(request.getRoleCatg());
        role.setRoleDesc(request.getRoleDesc());
        role.setOwnerType(request.getOwnerType());
        role.setOwnerId(request.getOwnerId());
        role.setDefaAccessPolicyId(request.getDefaAccessPolicyId());
        role.setStatus(request.getStatus());
        role.setLastUpdate(new Date());
        return roleRepository.save(role);
    }
}

