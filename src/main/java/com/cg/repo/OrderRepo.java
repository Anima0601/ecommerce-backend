package com.cg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Order;

public interface OrderRepo  extends JpaRepository<Order,Integer>{
	List<Order> findByCustomer_CustId(Integer custId);
}
