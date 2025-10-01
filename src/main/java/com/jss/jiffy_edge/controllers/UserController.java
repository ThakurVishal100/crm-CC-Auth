package com.jss.jiffy_edge.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jss.jiffy_edge.config.JwtUtil;
import com.jss.jiffy_edge.models.auth.UserResponse;
import com.jss.jiffy_edge.models.auth.UserUpdateRequest;
import com.jss.jiffy_edge.services.UserService;
import com.jss.jiffy_edge.services.auth.AuthService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping
	@Operation(summary = "Get all users", description = "This will fetch all the users based on the requester's role")
	public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String token) {
		// The token is expected to be in the format "Bearer <jwt>"
		String jwt = token.substring(7);
		Integer requesterId = jwtUtil.getUserIdFromToken(jwt);
		return (ResponseEntity<?>) userService.getAllUsers(requesterId);
	}

	@PostMapping("/update/{id}")
	@Operation(summary = "Update a user", description = "This will update an existing user's details")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest request) {
		return ResponseEntity.ok(authService.updateUser(id, request));
	}

	@PostMapping("/delete/{id}")
	@Operation(summary = "Delete a user", description = "This will deactivate an existing user (soft delete)")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		authService.deactivateUser(id);
		return ResponseEntity.noContent().build();
	}

}