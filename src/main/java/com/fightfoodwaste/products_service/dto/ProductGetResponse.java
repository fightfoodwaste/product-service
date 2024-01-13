package com.fightfoodwaste.products_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductGetResponse {
    private long id;
    private long business_id;
    private String product_name;
    private String product_description;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
}
