package com.cg.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.*;
import com.cg.entity.Customer;
import com.cg.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<SuccessDto> register(@Valid @RequestBody RegisterRequest request) {

        SuccessDto dto = authService.register(request);

        return ResponseEntity
                .status(201) 
                .body(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessDto> login(@RequestBody LoginRequest request,
                                            HttpServletResponse response) {

        AuthResponse auth = authService.login(request);

        SuccessDto dto = new SuccessDto();
        dto.setMessage("Login Successful");

        Cookie cookie = new Cookie("token", auth.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false); 
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);

        response.addCookie(cookie);

        return ResponseEntity
                .ok(dto); 
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); 
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }
        Customer customer= authService.getCustomer();
        return ResponseEntity.ok(
                Map.of(
                    "username", auth.getName(),
                    "custId", customer.getCustId()
                )
            );
    }
}