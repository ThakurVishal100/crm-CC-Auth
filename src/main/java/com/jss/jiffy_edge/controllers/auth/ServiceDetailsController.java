package com.jss.jiffy_edge.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.models.auth.ServiceDetailsRequest;
import com.jss.jiffy_edge.services.auth.PolicyService;
import com.jss.jiffy_edge.services.auth.ServiceDetailsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class ServiceDetailsController {

	private final ServiceDetailsService serviceDetailsService;

	@Autowired
	private PolicyService policyService;

	public ServiceDetailsController(ServiceDetailsService serviceDetailsService) {
		this.serviceDetailsService = serviceDetailsService;
	}

	@GetMapping("/getAllservices")
	@Operation(summary = "Get all services", description = "Returns a list of all available services.")
	public ResponseEntity<List<ServiceDetails>> getAllServices() {
		return ResponseEntity.ok(policyService.getAllServices());
	}

	@PostMapping("/createService")
	@Operation(summary = "Create new Service", description = "Create a new Service.")
	public ResponseEntity<?> createService(@RequestBody ServiceDetailsRequest request) {
		try {
			ServiceDetails createdService = serviceDetailsService.createService(request);
			return new ResponseEntity<>(createdService, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("updateService/{id}")
	@Operation(summary = "Update the existing service", description = "This will update the existing service present in the table")
	public ResponseEntity<?> updateService(@PathVariable Integer id, @RequestBody ServiceDetailsRequest request) {
		try {
			ServiceDetails updatedService = serviceDetailsService.updateService(id, request);
			return new ResponseEntity<>(updatedService, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
