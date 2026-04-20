package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.ProductSpec;

public interface ProductSpecRepo extends JpaRepository<ProductSpec,Integer> {

}
