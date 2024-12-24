package com.Inventary.Controller;

import com.Inventary.Service.InventoryService;
import com.Inventary.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    
  private final InventoryService service;
     
 

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
      return service.isinStock(skuCode);

    }
}
