package com.fightfoodwaste.products_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyStockMessage {

    private String order_id;
    private Long product_id;
    private int product_amount;

}