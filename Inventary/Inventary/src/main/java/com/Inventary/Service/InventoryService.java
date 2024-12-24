package com.Inventary.Service;

import java.util.List;

import com.Inventary.dto.InventoryResponse;

public interface InventoryService {

    public List<InventoryResponse> isinStock(List<String> skuCode);
}
