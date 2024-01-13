package com.fightfoodwaste.products_service.service;

import com.fightfoodwaste.products_service.dto.ProductStockGetResponse;
import com.fightfoodwaste.products_service.dto.SetQuantityRequest;
import com.fightfoodwaste.products_service.entity.ProductStockEntity;
import com.fightfoodwaste.products_service.exceptions.ProductNotFoundException;
import com.fightfoodwaste.products_service.repository.ProductStockRepository;
import com.fightfoodwaste.products_service.utility.ObjConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStockServiceImpl implements ProductStockService{

    private final ProductStockRepository repository;

    private final ObjConverter converter;
    @Override
    public boolean setQuantity(SetQuantityRequest request) {
        try{
            repository.setQuantity(request.getProduct_id(), request.getQuantity());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductStockGetResponse getProductStock(long product_id) throws ProductNotFoundException {
        ProductStockEntity productStock = repository.getProductStockEntity(product_id).orElseThrow(ProductNotFoundException::new);
        return converter.generateProductStockGetResponse(productStock);
    }
}
