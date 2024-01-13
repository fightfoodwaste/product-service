package com.fightfoodwaste.products_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ProductStockGetResponse {
    private long product_id;
    private int quantity;
}
