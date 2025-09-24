package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.dao.repo.auth.TblUserRolesRepository;
import com.jss.jiffy_edge.models.auth.CategorizedRolesResponse;
import com.jss.jiffy_edge.models.auth.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Integer SUPER_USER_ROLE_ID = 1;
    private static final String SUPER_USER_ROLE_NAME = "SUPER USER";

    @Autowired
    private TblUserRolesRepository roleRepository;

    @Override
    public TblUserRoles createRole(RoleRequest roleRequest) {
        if (SUPER_USER_ROLE_NAME.equalsIgnoreCase(roleRequest.getRoleName())) {
            throw new SecurityException("Creation of Super User role is not allowed.");
        }
        TblUserRoles role = new TblUserRoles();
        role.setCreationDate(new Date());
        return updateRoleFromRequest(role, roleRequest);
    }

    @Override
    public TblUserRoles updateRole(Integer roleId, RoleRequest roleRequest) {
        if (SUPER_USER_ROLE_ID.equals(roleId)) {
            throw new SecurityException("Updating the Super User role is not allowed.");
        }
        TblUserRoles role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        return updateRoleFromRequest(role, roleRequest);
    }

    @Override
    public void deleteRole(Integer roleId) {
        if (SUPER_USER_ROLE_ID.equals(roleId)) {
            throw new SecurityException("Deleting the Super User role is not allowed.");
        }
        TblUserRoles role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        role.setStatus(TblUserRoles.UserStatus.INACTIVE);
        role.setLastUpdate(new Date());
        roleRepository.save(role);
    }

    @Override
    public Object getAllRoles(Integer userRoleId) {
        if (userRoleId == null) {
            throw new SecurityException("You are not authorized to perform this action.");
        }

        TblUserRoles requesterRole = roleRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("Requesting user's role not found."));

        if (requesterRole.getRoleCatg() == TblUsers.UserCategory.SYSTEM_USER) {
            // System users see both system and external roles, categorized
            List<TblUserRoles> allRoles = roleRepository.findByRoleIdNot(SUPER_USER_ROLE_ID);
            List<TblUserRoles> systemRoles = allRoles.stream()
                    .filter(role -> role.getRoleCatg() == TblUsers.UserCategory.SYSTEM_USER)
                    .collect(Collectors.toList());
            List<TblUserRoles> externalRoles = allRoles.stream()
                    .filter(role -> role.getRoleCatg() == TblUsers.UserCategory.EXTERNAL_USER)
                    .collect(Collectors.toList());
            return new CategorizedRolesResponse(systemRoles, externalRoles);
        } else {
            // External users see only external roles
            return roleRepository.findByRoleCatgAndRoleIdNot(TblUsers.UserCategory.EXTERNAL_USER, SUPER_USER_ROLE_ID);
        }
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

