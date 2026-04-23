package com.cg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

	public Optional<Customer> findByUserUsername(String username);
}
