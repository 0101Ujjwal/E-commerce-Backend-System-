package com.product.service.impl;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.entity.product;
import com.product.repository.ProductRepo;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@Slf4j
public class ProductServiceImple implements ProductService {
    @Autowired
   private ProductRepo repo;


    @Override
    public void crete(ProductRequest productRequest) {
       product product = new product();
       product.setName(productRequest.getName());
       product.setDescription(productRequest.getDescription());
       product.setPrice(productRequest.getPrice());

       this.repo.save(product);
       log.info("Product {} is save",product.getId());

    }

    @Override
    public List<ProductResponse> getAllProduct() {
       List<product> productList = this.repo.findAll();

       return productList.stream().map(this :: mapToProductResponse).toList();

    }



    @Override
    public product getProductById(String id) {
        product product = this.repo.findById(id).orElseThrow();
        return product;
    }

    @Override
    public product getProductByName(String name) {
       product product = this.repo.findByName(name);
       return product;
    }

    @Override
    public void delete(String id) {
        product product = this.repo.findById(id).orElseThrow();
        this.repo.delete(product);
    }

    // mapper product tot productREsponse
    private ProductResponse mapToProductResponse(product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
