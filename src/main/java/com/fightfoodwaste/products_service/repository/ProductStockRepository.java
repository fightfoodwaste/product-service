package com.fightfoodwaste.products_service.repository;

import com.fightfoodwaste.products_service.entity.ProductStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStockEntity, Long> {

    @Query("UPDATE ProductStockEntity stock SET stock.quantity = :quantity WHERE stock.product.id = :product_id")
    @Modifying
    @Transactional
    void setQuantity(@Param("product_id") long product_id, @Param("quantity") int quantity);

    @Query("SELECT stock FROM ProductStockEntity stock WHERE stock.product.id = :product_id")
    Optional<ProductStockEntity> getProductStockEntity(@Param("product_id")long product_id);
}
