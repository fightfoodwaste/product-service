package com.fightfoodwaste.products_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCreateRequest {

    private long business_id;
    private String product_name;
    private String product_description;
}
