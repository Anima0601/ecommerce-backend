package com.cg.service;

import java.util.List;

import com.cg.dto.CartItemDto;

public interface CartService {
   
	String addToCart(Integer prodId,Integer custId,int qty);
	
	List<CartItemDto> getCartByCustomer(Integer custId);
	
	String removeFromCart(Integer custId,Integer prodId);
	
}
