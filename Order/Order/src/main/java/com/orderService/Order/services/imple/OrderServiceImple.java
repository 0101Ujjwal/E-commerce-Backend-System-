package com.orderService.Order.services.imple;

import com.orderService.Order.dto.InventoryResponse;
import com.orderService.Order.dto.OrderLineItemsDto;
import com.orderService.Order.dto.OrderRequest;
import com.orderService.Order.entity.Order;
import com.orderService.Order.entity.OrderLineItems;
import com.orderService.Order.repository.OrderRepo;
import com.orderService.Order.services.OrderServices;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImple implements OrderServices {

   
    private final OrderRepo orderRepository;

    
    private final  WebClient webClient;

    
    // PLACING THE ORDER 
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        // Create a new order instance
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString()); // Generate unique order number

        // Convert OrderRequestDto into OrderLineItems
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto) // Mapping OrderLineItemsDto to OrderLineItems
                .toList();

        // Set OrderLineItems into Order 
        order.setOrderLineItemsList(orderLineItems);

        // Fetch all SKU codes from the orderlineitems
       List<String> skuCode = order.getOrderLineItemsList().stream()
         .map(orderLineitem -> orderLineitem.getSkuCode())
         .toList();
        
        
        
        
        
    InventoryResponse[] inventoryResponseArray = webClient.get()
    	    .uri("http://localhost:8082/api/inventory", 
    	            uriBuilder -> uriBuilder.queryParam("skuCode", skuCode).build())
    	        .retrieve()
    	        .bodyToMono(InventoryResponse[].class)
    	        .block();

    boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
    	  .allMatch(InventoryResponse::isInStock);
                  
    if(allProductsInStock) {
        orderRepository.save(order);
    }else {
    	throw new IllegalArgumentException("Product is not in stock");
    }
    }

    // Helper method to map OrderLineItemsDto to OrderLineItems entity
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
    


}
