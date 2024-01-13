package com.fightfoodwaste.products_service.service;

import com.fightfoodwaste.products_service.dto.ProductStockGetResponse;
import com.fightfoodwaste.products_service.dto.SetQuantityRequest;
import com.fightfoodwaste.products_service.exceptions.ProductNotFoundException;

public interface ProductStockService {

    boolean setQuantity(SetQuantityRequest request);

    ProductStockGetResponse getProductStock(long product_id) throws ProductNotFoundException;
}
