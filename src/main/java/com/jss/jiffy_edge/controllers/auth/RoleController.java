package com.jss.jiffy_edge.controllers.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.models.auth.RoleRequest;
import com.jss.jiffy_edge.services.auth.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @Operation(summary="create new role",description = "this will create a new role")
    public ResponseEntity<TblUserRoles> createRole(@RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update the role",description = "this will update the existing role")
    public ResponseEntity<TblUserRoles> updateRole(@PathVariable Integer id, @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.updateRole(id, roleRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete the role",description = "this will delete the existing role")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "get all roles",description = "this will get all the roles")
    public ResponseEntity<List<TblUserRoles>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
