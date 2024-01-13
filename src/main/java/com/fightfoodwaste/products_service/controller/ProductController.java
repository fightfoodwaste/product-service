package com.fightfoodwaste.products_service.controller;

import com.fightfoodwaste.products_service.dto.ProductCreateRequest;
import com.fightfoodwaste.products_service.dto.ProductGetResponse;
import com.fightfoodwaste.products_service.entity.ProductEntity;
import com.fightfoodwaste.products_service.exceptions.ProductNotFoundException;
import com.fightfoodwaste.products_service.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductCreateRequest request){
        try {
            productService.createProduct(request);

            return ResponseEntity.ok().build();

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetResponse> getProduct(@PathVariable("id") long product_id){
        try{
            ProductGetResponse response = productService.getProduct(product_id);
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
