package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.ProductDto;
import com.cg.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired	
    private ProductService productService;

    // ✅ ADD PRODUCT
    @PostMapping
    public ResponseEntity<Integer> addProduct(@Valid @RequestBody ProductDto dto) {

        Integer id = productService.addProduct(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    // ✅ GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Integer id) {

        ProductDto product = productService.findProductById(id);

        return ResponseEntity.ok(product);
    }

    // ✅ GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        List<ProductDto> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }
}