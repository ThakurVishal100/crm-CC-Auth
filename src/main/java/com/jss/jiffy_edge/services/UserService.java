package com.jss.jiffy_edge.services;

import java.util.List;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.models.auth.UserResponse;

public interface UserService {

	List<UserResponse> getAllUsers();
	
	List<TblUserRoles> getAllRoles();
	
}
