package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.ShoppingCart;
import com.example.shoppingcart.model.request.view_model.CartItemViewModel;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Service
public interface ShoppingCartService {

    int getTotalProductsOfAUser(Principal principal, HttpServletRequest request);

    double getTotalPriceOfAUser(Principal principal, HttpServletRequest request);

    List<CartItemViewModel> getListCartItemViewModel(Principal principal, HttpServletRequest request);

    void createShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart findOne(int shoppingCardId);

    boolean updateShoppingCart(ShoppingCart shoppingCart);

    boolean deleteShoppingCart(int shoppingCartId);

    ShoppingCart findByUserName(String userName);
}
