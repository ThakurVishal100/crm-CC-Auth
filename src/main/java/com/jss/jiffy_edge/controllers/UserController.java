package com.jss.jiffy_edge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.models.auth.UserResponse;
import com.jss.jiffy_edge.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/allRoles")
	public List<TblUserRoles> getAllRoles() {
		return userService.getAllRoles();
	}

	@GetMapping("/allUsers")
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

}
