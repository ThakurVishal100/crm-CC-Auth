package com.jss.jiffy_edge.controllers.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;
import com.jss.jiffy_edge.models.auth.AddonPolicyRequest;
import com.jss.jiffy_edge.services.auth.AddonPolicyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addon-policies")
public class AddonPoliciesController {

    @Autowired
    private AddonPolicyService addonPolicyService;

    @GetMapping
    @Operation(summary = "Get all addon policies", description = "Returns a list of all addon policies.")
    public ResponseEntity<List<TblAccessAddonMap>> getAllAddonPolicies() {
        return ResponseEntity.ok(addonPolicyService.getAllAddonPolicies());
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new addon policy", description = "Creates a new addon policy.")
    public ResponseEntity<TblAccessAddonMap> createAddonPolicy(@RequestBody AddonPolicyRequest addonPolicyRequest) {
        return ResponseEntity.ok(addonPolicyService.createAddonPolicy(addonPolicyRequest));
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update an addon policy", description = "Updates an existing addon policy.")
    public ResponseEntity<TblAccessAddonMap> updateAddonPolicy(@PathVariable Integer id, @RequestBody AddonPolicyRequest addonPolicyRequest) {
        return ResponseEntity.ok(addonPolicyService.updateAddonPolicy(id, addonPolicyRequest));
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "Delete an addon policy", description = "Deletes an addon policy by setting its status to INACTIVE.")
    public ResponseEntity<Void> deleteAddonPolicy(@PathVariable Integer id) {
        addonPolicyService.deleteAddonPolicy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "Get addon policies by Role ID", description = "Returns a list of addon policies for a specific role.")
    public ResponseEntity<List<TblAccessAddonMap>> getAddonPoliciesByRoleId(@PathVariable Integer roleId) {
        return ResponseEntity.ok(addonPolicyService.getAddonPoliciesByRoleId(roleId));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get addon policies by User ID", description = "Returns a list of addon policies for a specific user.")
    public ResponseEntity<List<TblAccessAddonMap>> getAddonPoliciesByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(addonPolicyService.getAddonPoliciesByUserId(userId));
    }
}

