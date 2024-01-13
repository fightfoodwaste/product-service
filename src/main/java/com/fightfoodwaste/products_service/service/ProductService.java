package com.fightfoodwaste.products_service.service;

import com.fightfoodwaste.products_service.dto.ProductCreateRequest;
import com.fightfoodwaste.products_service.dto.ProductGetResponse;
import com.fightfoodwaste.products_service.exceptions.ProductNotFoundException;

public interface ProductService {

    void createProduct(ProductCreateRequest request);

    ProductGetResponse getProduct(long product_id) throws ProductNotFoundException;
}
