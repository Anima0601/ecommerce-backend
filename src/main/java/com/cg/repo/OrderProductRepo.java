package com.cg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.OrderProduct;

public interface OrderProductRepo extends JpaRepository<OrderProduct,Integer> {
	List<OrderProduct> findByOrder_Id(Integer orderId);
}
