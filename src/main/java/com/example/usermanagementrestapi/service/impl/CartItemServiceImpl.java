package com.example.usermanagementrestapi.service.impl;

import com.example.usermanagementrestapi.entity.CartItem;
import com.example.usermanagementrestapi.repository.CartItemRepository;
import com.example.usermanagementrestapi.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void createCartItem(CartItem cartItem) {

        cartItemRepository.save(cartItem);
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {

        try {
            cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public CartItem findOne(int cartItemId) {
        return cartItemRepository.getOne(cartItemId);
    }

    @Override
    public boolean deleteCartItem(int cartItemId) {

        try {
            cartItemRepository.deleteById(cartItemId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public CartItem findFirstCartItemByShoppingCartIdAndProductId(int shoppingCartId, int productId) {

        try {
            return cartItemRepository.findFirstCartItemByShoppingCartIdAndProductId(shoppingCartId, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
