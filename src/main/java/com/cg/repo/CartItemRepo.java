package com.cg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem,Integer>{
	 List<CartItem> findByCustomer_CustId(Integer custId);

	    Optional<CartItem> findByCustomer_CustIdAndProduct_ProdId(Integer custId, Integer prodId);

	    void deleteByCustomer_CustIdAndProduct_ProdId(Integer custId, Integer prodId);
}
