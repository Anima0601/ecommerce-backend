package com.cg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.*;
import com.cg.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ REGISTER
    @PostMapping("/register")
    public SuccessDto register(@Valid @RequestBody RegisterRequest request) {

       SuccessDto dto = authService.register(request);
       return dto;
    }

    @PostMapping("/login")
    public SuccessDto login(@RequestBody LoginRequest request, HttpServletResponse response) {

        AuthResponse auth = authService.login(request);
        SuccessDto dto = new SuccessDto();
        dto.setMessage("Login Successful");

        Cookie cookie = new Cookie("token", auth.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false); 
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);

        response.addCookie(cookie);

        return dto;
    }
}