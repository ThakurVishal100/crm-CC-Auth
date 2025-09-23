package com.jss.jiffy_edge.controllers;

import com.jss.jiffy_edge.models.auth.UserResponse;
import com.jss.jiffy_edge.models.auth.UserUpdateRequest;
import com.jss.jiffy_edge.services.UserService;
import com.jss.jiffy_edge.services.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@GetMapping
	@Operation(summary = "get all users",description = "this will fetch all the users")
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	@PutMapping("/{id}")
	@Operation(summary="update the user",description = "this will update the existing user")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest request) {
		return ResponseEntity.ok(authService.updateUser(id, request));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "delete the user",description = "this will delete the existing user")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		authService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
