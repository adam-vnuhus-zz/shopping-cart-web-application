package com.example.usermanagementrestapi.service.impl;

import com.example.usermanagementrestapi.entity.ShoppingCart;
import com.example.usermanagementrestapi.repository.ShoppingCartRepository;
import com.example.usermanagementrestapi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public void createShoppingCart(ShoppingCart shoppingCart) {

        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findOne(int shoppingCardId) {
        return shoppingCartRepository.getOne(shoppingCardId);
    }

    @Override
    public boolean updateShoppingCart(ShoppingCart shoppingCart) {

        try {
            shoppingCartRepository.save(shoppingCart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteShoppingCart(int shoppingCartId) {

        try {
            shoppingCartRepository.deleteById(shoppingCartId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ShoppingCart findByUserName(String userName) {

        try {
            return shoppingCartRepository.findByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
