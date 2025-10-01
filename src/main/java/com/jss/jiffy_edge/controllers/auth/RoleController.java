package com.jss.jiffy_edge.controllers.auth;

import com.jss.jiffy_edge.config.JwtUtil;
import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.models.auth.RoleRequest;
import com.jss.jiffy_edge.services.auth.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @Operation(summary="create new role",description = "this will create a new role")
    public ResponseEntity<TblUserRoles> createRole(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "update the role",description = "this will update the existing role")
    public ResponseEntity<TblUserRoles> updateRole(@PathVariable Integer id, @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.updateRole(id, roleRequest));
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "delete the role",description = "this will delete the existing role")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "get all roles", description = "this will get all the roles based on user's permission")
    public ResponseEntity<Object> getAllRoles(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        Integer userRoleId = jwtUtil.getRoleIdFromToken(token);
        return ResponseEntity.ok(roleService.getAllRoles(userRoleId));
    }
}