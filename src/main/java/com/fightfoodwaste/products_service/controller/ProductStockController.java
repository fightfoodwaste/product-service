package com.fightfoodwaste.products_service.controller;

import com.fightfoodwaste.products_service.dto.ProductGetResponse;
import com.fightfoodwaste.products_service.dto.ProductStockGetResponse;
import com.fightfoodwaste.products_service.dto.SetQuantityRequest;
import com.fightfoodwaste.products_service.exceptions.ProductNotFoundException;
import com.fightfoodwaste.products_service.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product_stock")
@RequiredArgsConstructor
public class ProductStockController {

    private final ProductStockService service;

    @PostMapping
    public ResponseEntity setQuantity(@RequestBody SetQuantityRequest request){
        try{
            Boolean success = service.setQuantity(request);
            if(success){
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductStockGetResponse> getProductStock(@PathVariable("id") long product_id){
        try{
            ProductStockGetResponse response = service.getProductStock(product_id);
            return ResponseEntity.ok().body(response);
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(404).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
