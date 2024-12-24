package com.orderService.Order.controller;

import com.orderService.Order.dto.OrderRequest;
import com.orderService.Order.services.OrderServices;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

   
    private final OrderServices services;

    // Place order
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
       
    	services.placeOrder(orderRequest);
    	return "Order Places Successfully";
    }
}
