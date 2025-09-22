package com.jss.jiffy_edge.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails;
import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.dao.entities.auth.SysAccessLevel;
import com.jss.jiffy_edge.models.SystemAccessPolicyRequest;
import com.jss.jiffy_edge.models.auth.PolicyResponse;
import com.jss.jiffy_edge.services.auth.PolicyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class PolicyController {

	@Autowired
	private  PolicyService policyService;

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

	@PostMapping("/access-policies")
	@Operation(summary = "Create a new access policy", description = "Creates a new system access policy.")
	public ResponseEntity<AccessPolicyDetails> createAccessPolicy(
			@RequestBody SystemAccessPolicyRequest accessPolicyRequest) {
		return ResponseEntity.ok(policyService.createAccessPolicy(accessPolicyRequest));
	}

	@PutMapping("/access-policies/{policyId}")
	@Operation(summary = "Update an access policy", description = "Updates an existing system access policy.")
	public ResponseEntity<AccessPolicyDetails> updateAccessPolicy(@PathVariable Long policyId,
			@RequestBody SystemAccessPolicyRequest accessPolicyRequest) {
		return ResponseEntity.ok(policyService.updateAccessPolicy(policyId, accessPolicyRequest));
	}

	@DeleteMapping("/access-policies/{policyId}")
	@Operation(summary = "Delete an access policy", description = "Deletes a system access policy by its ID.")
	public ResponseEntity<Void> deleteAccessPolicy(@PathVariable Long policyId) {
		policyService.deleteAccessPolicy(policyId);
		return ResponseEntity.noContent().build();
	}
}