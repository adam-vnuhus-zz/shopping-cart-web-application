package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.ShoppingCart;
import com.example.shoppingcart.model.request.view_model.CartItemViewModel;
import com.example.shoppingcart.repository.ShoppingCartRepository;
import com.example.shoppingcart.service.ShoppingCartService;
import com.example.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public int getTotalProductsOfAUser(Principal principal, HttpServletRequest request) {

        int totalProducts = 0;
        String username = userService.getUser(request);

        try {
            if (principal != null) {
                ShoppingCart shoppingCartEntity = shoppingCartService.findByUserName(username);
                if (shoppingCartEntity != null) {

                    for (CartItem cartItem : shoppingCartEntity.getListCartProducts()) {
                        totalProducts += cartItem.getAmount();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalProducts;
    }

    @Override
    public double getTotalPriceOfAUser(Principal principal, HttpServletRequest request) {

        double totalPrice = 0;
        String username = userService.getUser(request);

        try {
            if (principal != null) {
                ShoppingCart shoppingCartEntity = shoppingCartService.findByUserName(username);
                if (shoppingCartEntity != null) {

                    for (CartItem cartItem : shoppingCartEntity.getListCartProducts()) {

                        double price = cartItem.getAmount() * cartItem.getProduct().getPrice();
                        totalPrice += price;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalPrice;
    }

    @Override
    public List<CartItemViewModel> getListCartItemViewModel(Principal principal, HttpServletRequest request) {

        List<CartItemViewModel> cartItemViewModels = new ArrayList<>();

        String username = userService.getUser(request);
        DecimalFormat df = new DecimalFormat("###,###.###");

        try {
            if (principal != null) {
                ShoppingCart shoppingCartEntity = shoppingCartService.findByUserName(username);
                if (shoppingCartEntity != null) {

                    for (CartItem cartItem : shoppingCartEntity.getListCartProducts()) {
                        double temp = 0;
                        CartItemViewModel cartItemViewModel = new CartItemViewModel();
                        cartItemViewModel.setId(cartItem.getCartItemId());
                        cartItemViewModel.setProductId(cartItem.getProduct().getProductId());
                        cartItemViewModel.setProductName(cartItem.getProduct().getName());
                        cartItemViewModel.setThumbnail(cartItem.getProduct().getThumbnail());
                        cartItemViewModel.setAmount(cartItem.getAmount());
                        temp = cartItem.getProduct().getPrice();
                        cartItemViewModel.setPrice(df.format(temp));
                        double price = cartItem.getAmount() * cartItem.getProduct().getPrice();
                        cartItemViewModel.setTotalPrice(df.format(price));
                        cartItemViewModels.add(cartItemViewModel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItemViewModels;
    }

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
