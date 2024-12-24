package com.Inventary.Service.impl;

import com.Inventary.Repository.InventoryRepo;
import com.Inventary.Service.InventoryService;
import com.Inventary.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    
  
    private final InventoryRepo repo;
    
    @Transactional(readOnly = true)
  public List<InventoryResponse> isinStock(List<String> skuCode) {
		return this.repo.findBySkuCodeIn(skuCode)
	            .stream()
	            .map(inventory ->  InventoryResponse.builder()
	                .skuCode(inventory.getSkuCode())  // Corrected to match the field name in InventoryResponse
	                .isInStock(inventory.getQuantity() > 0)
	                .build()
	                ).toList();
	                
	              // Using collect to ensure compatibility across all Java versions
	}
}
