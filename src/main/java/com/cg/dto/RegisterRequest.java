package com.cg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	   @NotBlank(message = "Username is required")
	    private String username;

	    @Size(min = 4, message = "Password must be at least 4 characters")
	    private String password;

	    @NotBlank(message = "Name is required")
	    private String name;

	    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	    private String phoneNo;
	
	

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPhoneNo() {
	        return phoneNo;
	    }

	    public void setPhoneNo(String phoneNo) {
	        this.phoneNo = phoneNo;
	    }
}
