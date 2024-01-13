package com.fightfoodwaste.products_service.service;

import com.fightfoodwaste.products_service.dto.ProductCreateRequest;
import com.fightfoodwaste.products_service.dto.ProductGetResponse;
import com.fightfoodwaste.products_service.entity.ProductEntity;
import com.fightfoodwaste.products_service.exceptions.ProductNotFoundException;
import com.fightfoodwaste.products_service.repository.ProductRepository;
import com.fightfoodwaste.products_service.utility.ObjConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.security.auth.login.AccountNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    private final ObjConverter converter;
    @Override
    public void createProduct(ProductCreateRequest request) {
        ProductEntity productEntity = converter.createProductEntity(request);
        repository.save(productEntity);
    }

    @Override
    public ProductGetResponse getProduct(long product_id) throws ProductNotFoundException{
        ProductEntity productEntity = repository.findById(product_id).orElseThrow(ProductNotFoundException::new);
        return converter.generateProductGetResponse(productEntity);
    }
}
