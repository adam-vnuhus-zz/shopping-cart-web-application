package com.example.usermanagementrestapi.controller;

import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.service.CategoryService;
import com.example.usermanagementrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("product") ProductDto productDto) {

        List<ProductDto> productDtoList = productService.getListProductNew();
        List<ProductDto> rs = new ArrayList<>();
        rs.addAll(productDtoList);
        model.addAttribute("products", rs);
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
