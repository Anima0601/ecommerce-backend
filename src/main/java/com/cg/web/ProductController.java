package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ProductDto;
import com.cg.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
       
    @Autowired	
	private ProductService productService;
    
    @PostMapping
    public Integer addProduct(@Valid @RequestBody ProductDto dto) {
    	return productService.addProduct(dto);
    }
    
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Integer id) {
    	return productService.findProductById(id);
    }
    
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
