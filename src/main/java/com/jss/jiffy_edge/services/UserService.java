package com.jss.jiffy_edge.services;

import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<?> getAllUsers(Integer requesterId);

}