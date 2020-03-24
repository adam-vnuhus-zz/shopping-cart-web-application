package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {

    void createShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart findOne(int shoppingCardId);

    boolean updateShoppingCart(ShoppingCart shoppingCart);

    boolean deleteShoppingCart(int shoppingCartId);

    ShoppingCart findByUserName(String userName);
}
