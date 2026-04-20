package com.cg.service;

import java.util.List;

import com.cg.dto.ProductDto;

public interface ProductService {
	
	Integer addProduct(ProductDto dto);
	
	List<ProductDto> getAllProducts();
	
	ProductDto findProductById(Integer id);

}
