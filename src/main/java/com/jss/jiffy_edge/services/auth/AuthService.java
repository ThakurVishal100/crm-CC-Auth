package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.models.auth.UserLoginRequest;
import com.jss.jiffy_edge.models.auth.UserResponse;
import com.jss.jiffy_edge.models.auth.UserSignupRequest;
import com.jss.jiffy_edge.models.auth.UserUpdateRequest;

public interface AuthService {
    UserResponse signup(UserSignupRequest request);
    UserResponse login(UserLoginRequest request);
    UserResponse updateUser(Integer userId, UserUpdateRequest request);
    void deactivateUser(Integer userId);
}