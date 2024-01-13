package com.fightfoodwaste.products_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetQuantityRequest {
    private long product_id;
    private int quantity;
}
