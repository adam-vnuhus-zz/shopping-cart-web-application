package com.example.shoppingcart.controller.frontend.client;

import com.example.shoppingcart.model.request.view_model.ShoppingCartViewModel;
import com.example.shoppingcart.service.ShoppingCartService;
import com.example.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.text.DecimalFormat;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String cart(Model model,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       Principal principal) {

        userService.checkUser(response, request, principal);

        ShoppingCartViewModel shoppingCartViewModel = new ShoppingCartViewModel();

        DecimalFormat df = new DecimalFormat("###,###.###");

        shoppingCartViewModel.setProductAmount(shoppingCartService.getTotalProductsOfAUser(principal, request));
        shoppingCartViewModel.setTotalPrice(df.format(shoppingCartService.getTotalPriceOfAUser(principal, request)));
        shoppingCartViewModel.setCartItemViewModels(shoppingCartService.getListCartItemViewModel(principal, request));

        model.addAttribute("shoppingCartViewModel", shoppingCartViewModel);

        return "cart";
    }
}
