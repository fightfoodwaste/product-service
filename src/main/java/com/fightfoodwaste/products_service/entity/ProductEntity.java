package com.fightfoodwaste.products_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "business_id")
    private long business_id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_description")
    private String product_description;
    @Column(name = "created_at")
    private Long created_at;
    @Column(name = "modified_at")
    private Long modified_at;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductStockEntity stock;

}
