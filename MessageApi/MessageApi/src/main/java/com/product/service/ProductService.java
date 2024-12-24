package com.product.service;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.entity.product;

import java.util.List;

public interface ProductService  {

   void crete(ProductRequest productRequest);
    List<ProductResponse> getAllProduct();
    product getProductById(String id);
    product getProductByName(String name);
    void delete(String id);

}
