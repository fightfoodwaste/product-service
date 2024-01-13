package com.fightfoodwaste.products_service.utility;

import com.fightfoodwaste.products_service.dto.ProductCreateRequest;
import com.fightfoodwaste.products_service.dto.ProductGetResponse;
import com.fightfoodwaste.products_service.dto.ProductStockGetResponse;
import com.fightfoodwaste.products_service.entity.ProductEntity;
import com.fightfoodwaste.products_service.entity.ProductStockEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class ObjConverter {

    private Long getCurrentTimestampMillis(){
        LocalDateTime timeNow = LocalDateTime.now();
        ZoneOffset zoneOffset = ZoneOffset.of("+01:00");
        ZonedDateTime zonedDateTime = timeNow.atOffset(zoneOffset).toZonedDateTime();
        return zonedDateTime.toInstant().toEpochMilli();
    }

    public LocalDateTime getCurrentTimestamp(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        ZoneOffset zoneOffset = ZoneOffset.of("+01:00"); // Adjust this offset as needed
        return instant.atOffset(zoneOffset).toLocalDateTime();
    }

    public ProductStockEntity createProductStockEntity(){
        ProductStockEntity productStockEntity = new ProductStockEntity();
        productStockEntity.setQuantity(0);
        return productStockEntity;
    }

    public ProductEntity createProductEntity(ProductCreateRequest request){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBusiness_id(request.getBusiness_id());
        productEntity.setProduct_name(request.getProduct_name());
        productEntity.setProduct_description(request.getProduct_description());
        productEntity.setCreated_at(getCurrentTimestampMillis());
        productEntity.setModified_at(getCurrentTimestampMillis());

        ProductStockEntity productStockEntity = createProductStockEntity();
        productEntity.setStock(productStockEntity);
        productStockEntity.setProduct(productEntity);

        return productEntity;
    }

    public ProductGetResponse generateProductGetResponse(ProductEntity entity){
        return new ProductGetResponse(entity.getId(),
                entity.getBusiness_id(),
                entity.getProduct_name(),
                entity.getProduct_description(),
                getCurrentTimestamp(entity.getCreated_at()),
                getCurrentTimestamp(entity.getModified_at()));
    }

    public ProductStockGetResponse generateProductStockGetResponse(ProductStockEntity entity){
        return new ProductStockGetResponse(entity.getProduct().getId(),
                entity.getQuantity());
    }
}
