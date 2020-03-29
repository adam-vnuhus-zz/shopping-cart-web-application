package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    @Query(value = "SELECT * from shopping_cart c " +
            "WHERE :userName IS NULL OR c.username = :userName " +
            "ORDER BY c.shopping_cart_id DESC LIMIT 1", nativeQuery = true)
    ShoppingCart findByUserName(@Param("userName") String userName);
}
