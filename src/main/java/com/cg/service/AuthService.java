package com.cg.service;

import com.cg.dto.LoginRequest;
import com.cg.dto.RegisterRequest;
import com.cg.dto.SuccessDto;
import com.cg.entity.Customer;
import com.cg.entity.User;
import com.cg.dto.AuthResponse;

public interface AuthService {

	SuccessDto register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
    
    public User getCurrentUser();
    public Customer getCustomer();
}