package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.LoginRequest;
import com.cg.dto.RegisterRequest;
import com.cg.dto.SuccessDto;
import com.cg.dto.AuthResponse;
import com.cg.entity.Authority;
import com.cg.entity.Customer;
import com.cg.entity.User;
import com.cg.exception.AlreadyExistsException;
import com.cg.exception.NotFoundException;
import com.cg.exception.BadRequestException;
import com.cg.repo.AuthorityRepo;
import com.cg.repo.CustomerRepo;
import com.cg.repo.UserRepo;
import com.cg.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private AuthorityRepo authorityRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public SuccessDto register(RegisterRequest request) {
    	
    	SuccessDto dto = new SuccessDto();


        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new AlreadyExistsException("Username already exists");
        }

        if (request.getPassword().length() < 4) {
            throw new BadRequestException("Password must be at least 4 characters");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        Authority role = authorityRepo.findByRole("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("Default role not found"));

        user.setAuthorities(List.of(role));

        User savedUser = userRepo.save(user);

        // create customer
        Customer customer = new Customer();
        customer.setCustName(request.getName());
        customer.setPhoneNo(request.getPhoneNo());
        customer.setUser(savedUser);

        customerRepo.save(customer);

        dto.setMessage("Registered Successfully");
		return dto;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

 
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Invalid username"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}