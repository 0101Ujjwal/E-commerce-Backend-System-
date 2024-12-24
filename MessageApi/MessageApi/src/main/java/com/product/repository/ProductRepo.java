package com.product.repository;

import com.product.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ProductRepo extends JpaRepository<product, String> {
    product findByName(String name);
}
