package com.cg.service;

import com.cg.dto.LoginRequest;
import com.cg.dto.RegisterRequest;
import com.cg.dto.AuthResponse;

public interface AuthService {

	String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}