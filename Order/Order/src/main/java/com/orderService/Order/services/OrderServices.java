package com.orderService.Order.services;

import com.orderService.Order.dto.OrderRequest;

import java.util.List;

public interface OrderServices {
    public void placeOrder(OrderRequest orderRequest);
   
}
