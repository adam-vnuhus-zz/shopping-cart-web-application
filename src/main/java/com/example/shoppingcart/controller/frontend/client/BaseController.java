package com.example.shoppingcart.controller.frontend.client;

import com.example.shoppingcart.entity.ShoppingCart;
import com.example.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public class BaseController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    public void checkCookie(HttpServletResponse response,
                            HttpServletRequest request,
                            Principal principal) {

        Cookie[] cookie = request.getCookies();

        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            ShoppingCart cartEntity = shoppingCartService.findByUserName(username);
            if (cartEntity != null) {
                Cookie cookie1 = new Cookie("user", cartEntity.getUsername());
                cookie1.setPath("/");
                response.addCookie(cookie1);
            } else {
                ShoppingCart cart = new ShoppingCart();
                cart.setUsername(username);
                shoppingCartService.createShoppingCart(cart);

                Cookie cookie2 = new Cookie("user", username);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
        }
    }

}
