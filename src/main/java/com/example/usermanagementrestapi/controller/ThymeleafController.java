package com.example.usermanagementrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/")
    public String index() {
        // Tra ve file index.html
        //model.addAttribute("product", product);
        return "index";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/product-details")
    public String productDetails() {
        return "product-details";
    }

    @GetMapping("/wishlist")
    public String wishList() {
        return "wishlist";
    }

    @GetMapping("/shop")
    public  String shop() {
        return "shop";
    }

    @GetMapping("/login-register")
    public String loginRegister() {
        return "login-register";
    }
}
