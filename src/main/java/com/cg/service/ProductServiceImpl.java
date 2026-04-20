package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ProductDto;
import com.cg.entity.Product;
import com.cg.repo.ProductRepo;
import com.cg.exception.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Integer addProduct(ProductDto dto) {
		Product product = new Product();
		product.setProdName(dto.getProdName());
		product.setPrice(dto.getPrice());
		product.setProdDesc(dto.getProdDesc());
		product.setProdImage(dto.getProdImage());
		product.setStock(dto.getStock());
		
		Product savedproduct = productRepo.save(product);
		
		return savedproduct.getProdId();
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> productList = productRepo.findAll();
		List<ProductDto> dtoList = new ArrayList<>();
		for(Product product:productList) {
			ProductDto dto = new ProductDto();
			dto.setProdName(product.getProdName());
			dto.setPrice(product.getPrice());
			dto.setProdDesc(product.getProdDesc());
			dto.setStock(product.getStock());
			dto.setProdId(product.getProdId()); 
			dto.setProdImage(product.getProdImage());
			
			dtoList.add(dto);
		}
		
		return dtoList;
		
	}

	@Override
	public ProductDto findProductById(Integer id) {
		Optional<Product> optProduct = productRepo.findById(id);
		if(optProduct.isEmpty()) {
			throw new NotFoundException("Product not found with id: " + id);
		}
		Product product = optProduct.get();
		ProductDto dto = new ProductDto();
		
		dto.setProdName(product.getProdName());
		dto.setPrice(product.getPrice());
		dto.setProdDesc(product.getProdDesc());
		dto.setStock(product.getStock());
		dto.setProdId(product.getProdId()); 
		dto.setProdImage(product.getProdImage());
		
		return dto;
	}

}
