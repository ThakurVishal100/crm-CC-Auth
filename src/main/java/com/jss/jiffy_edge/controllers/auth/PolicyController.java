package com.jss.jiffy_edge.controllers.auth;

import java.nio.file.AccessDeniedException;
import java.util.List;

import com.jss.jiffy_edge.config.JwtUtil;
import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyMaster;
import com.jss.jiffy_edge.models.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails;
import com.jss.jiffy_edge.dao.entities.auth.SysAccessLevel;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.services.auth.PolicyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class PolicyController {

    @Autowired
    private  PolicyService policyService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/permissions/grant-page")
    @Operation(summary = "Get data for permission grant page", description = "Returns a list of all services and their associated menus for the permission grant page.")
    public ResponseEntity<List<PermissionGrantResponse>> getPermissionsForGrantPage() {
        return ResponseEntity.ok(policyService.getPermissionsForGrantPage());
    }

    @PostMapping("/permissions/grant")
    @Operation(summary = "Grant permissions to a role", description = "Grants a specific policy to a role, with hierarchical checks.")
    public ResponseEntity<String> grantPermission(@RequestHeader("Authorization") String token, @RequestBody GrantPermissionRequest request) throws AccessDeniedException {
        Integer requesterRoleId = jwtUtil.getRoleIdFromToken(token.substring(7));
        policyService.grantPermissionToRole(requesterRoleId, request);
        return ResponseEntity.ok("Permission granted successfully.");
    }

    @PostMapping("/master-policies/create")
    @Operation(summary = "Create a new master policy", description = "Creates a new master policy.")
    public ResponseEntity<AccessPolicyMaster> createMasterPolicy(@RequestBody MasterPolicyRequest request) {
        return ResponseEntity.ok(policyService.createMasterPolicy(request));
    }

    @PostMapping("/master-policies/update/{policyId}")
    @Operation(summary = "Update a master policy", description = "Updates an existing master policy.")
    public ResponseEntity<AccessPolicyMaster> updateMasterPolicy(@PathVariable Integer policyId, @RequestBody MasterPolicyRequest request) {
        return ResponseEntity.ok(policyService.updateMasterPolicy(policyId, request));
    }

    @GetMapping("/master-policies")
    @Operation(summary = "Get all master policies", description = "Returns a list of all master policies, hiding super user policies for non-super users. Can be filtered by status.")
    public ResponseEntity<List<AccessPolicyMaster>> getAllMasterPolicies(@RequestHeader("Authorization") String token) {
        Integer requesterRoleId = jwtUtil.getRoleIdFromToken(token.substring(7));
        return ResponseEntity.ok(policyService.getAllMasterPolicies(requesterRoleId));
    }

    @PostMapping("/master-policies/remove/{policyId}")
    @Operation(summary = "Delete a master policy", description = "Deletes a master policy by setting its status to INACTIVE.")
    public ResponseEntity<Void> deleteMasterPolicy(@PathVariable Integer policyId) {
        policyService.deleteMasterPolicy(policyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/policies/role/{roleId}")
    @Operation(summary = "Get policies by Role ID", description = "Returns a list of policies associated with a specific role.")
    public ResponseEntity<List<PolicyResponse>> getPoliciesByRoleId(@PathVariable Integer roleId) {
        return ResponseEntity.ok(policyService.getPoliciesByRoleId(roleId));
    }

    @GetMapping("/policies/applied/role/{roleId}")
    @Operation(summary = "Get applied policies by Role ID", description = "Returns a list of applied policies associated with a specific role from the view.")
    public ResponseEntity<List<PolicyResponse>> getAppliedPoliciesByRoleId(@PathVariable Integer roleId) {
        return ResponseEntity.ok(policyService.getAppliedPoliciesByRoleId(roleId));
    }

    @GetMapping("/policies/user/{userId}")
    @Operation(summary = "Get policies by User ID", description = "Returns a list of policies associated with a specific user.")
    public ResponseEntity<List<PolicyResponse>> getPoliciesByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(policyService.getPoliciesByUserId(userId));
    }

    @GetMapping("/sys-access-levels")
    @Operation(summary = "Get all system access levels", description = "Returns a list of all available system access levels.")
    public ResponseEntity<List<SysAccessLevel>> getAllSysAccessLevels() {
        return ResponseEntity.ok(policyService.getAllSysAccessLevels());
    }

    @GetMapping("/access-policies")
    @Operation(summary = "Get all access policies", description = "Returns a list of all access policies.")
    public ResponseEntity<List<AccessPolicyDetails>> getAllAccessPolicies() {
        return ResponseEntity.ok(policyService.getAllAccessPolicies());
    }

    @PostMapping("/access-policies/create")
    @Operation(summary = "Create a new access policy", description = "Creates a new system access policy.")
    public ResponseEntity<AccessPolicyDetails> createAccessPolicy(
            @RequestBody SystemAccessPolicyRequest accessPolicyRequest) {
        return ResponseEntity.ok(policyService.createAccessPolicy(accessPolicyRequest));
    }

    @PostMapping("/access-policies/update/{policyId}")
    @Operation(summary = "Update an access policy", description = "Updates an existing system access policy.")
    public ResponseEntity<AccessPolicyDetails> updateAccessPolicy(@PathVariable Long policyId,
                                                                  @RequestBody SystemAccessPolicyRequest accessPolicyRequest) {
        return ResponseEntity.ok(policyService.updateAccessPolicy(policyId, accessPolicyRequest));
    }

    @PostMapping("/policies/user/update")
    @Operation(summary = "Update a user's policy", description = "Allows an external user to update a policy for themselves or a user under them.")
    public ResponseEntity<String> updateUserPolicy(@RequestHeader("Authorization") String token, @RequestBody UpdateUserPolicyRequest request) throws AccessDeniedException {
        Integer requesterId = jwtUtil.getUserIdFromToken(token.substring(7));
        policyService.updateUserPolicy(requesterId, request);
        return ResponseEntity.ok("User policy updated successfully.");
    }

    @PostMapping("/access-policies/remove/{policyId}")
    @Operation(summary = "Delete an access policy", description = "Deletes a system access policy by its ID.")
    public ResponseEntity<Void> deleteAccessPolicy(@PathVariable Long policyId) {
        policyService.deleteAccessPolicy(policyId);
        return ResponseEntity.noContent().build();
    }
}