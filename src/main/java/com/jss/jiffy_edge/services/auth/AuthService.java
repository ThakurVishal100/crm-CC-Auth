package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.models.auth.UserLoginRequest;
import com.jss.jiffy_edge.models.auth.UserResponse;
import com.jss.jiffy_edge.models.auth.UserSignupRequest;

public interface AuthService {
    UserResponse signup(UserSignupRequest request);
    UserResponse login(UserLoginRequest request);
}

