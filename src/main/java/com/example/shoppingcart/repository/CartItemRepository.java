package com.example.shoppingcart.repository;

import com.example.shoppingcart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query(value = "SELECT * FROM cart_item cp " +
            "WHERE cp.shopping_cart_id = :shoppingCartId  " +
            "AND cp.product_id = :productId " +
            "ORDER BY cp.cart_item_id DESC LIMIT 1", nativeQuery = true)
    CartItem findFirstCartItemByShoppingCartIdAndProductId(@Param("shoppingCartId") int shoppingCartId, @Param("productId") int productId);

}
