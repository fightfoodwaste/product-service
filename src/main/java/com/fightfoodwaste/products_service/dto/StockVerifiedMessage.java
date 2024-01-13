package com.fightfoodwaste.products_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class StockVerifiedMessage {
    private String order_id;
    private boolean available;
}
