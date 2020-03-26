package com.example.usermanagementrestapi.controller.frontend.client;

import com.example.usermanagementrestapi.entity.CartItem;
import com.example.usermanagementrestapi.entity.ShoppingCart;
import com.example.usermanagementrestapi.model.request.view_model.CartItemViewModel;
import com.example.usermanagementrestapi.model.request.view_model.ProductViewModel;
import com.example.usermanagementrestapi.model.request.view_model.ShoppingCartViewModel;
import com.example.usermanagementrestapi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController extends BaseController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/add")
    public String cart(Model model,
                       @Valid @ModelAttribute("productname") ProductViewModel productName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       Principal principal) {

        this.checkCookie(response, request, principal);

        ShoppingCartViewModel shoppingCartViewModel = new ShoppingCartViewModel();
        int productAmount = 0;
        double totalPrice = 0;

        List<CartItemViewModel> cartItemViewModels = new ArrayList<>();
        String username = getUser(request);
        DecimalFormat df = new DecimalFormat("###,###.###");

        try {
            if (principal != null) {
                ShoppingCart shoppingCartEntity = shoppingCartService.findByUserName(username);
                if (shoppingCartEntity != null) {
                    productAmount = shoppingCartEntity.getListCartProducts().size();
                    for (CartItem cartItem : shoppingCartEntity.getListCartProducts()) {
                        CartItemViewModel cartItemViewModel = new CartItemViewModel();
                        cartItemViewModel.setId(cartItem.getCartItemId());
                        cartItemViewModel.setProductId(cartItem.getProduct().getProductId());
                        cartItemViewModel.setProductName(cartItem.getProduct().getName());
                        cartItemViewModel.setThumbnail(cartItem.getProduct().getThumbnail());
                        cartItemViewModel.setAmount(cartItem.getAmount());
                        double price = cartItem.getAmount() * cartItem.getProduct().getPrice();
                        cartItemViewModel.setPrice(df.format(price));
                        totalPrice += price;
                        cartItemViewModels.add(cartItemViewModel);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        shoppingCartViewModel.setProductAmount(productAmount);
        shoppingCartViewModel.setTotalPrice(df.format(totalPrice));
        shoppingCartViewModel.setCartItemViewModels(cartItemViewModels);

        model.addAttribute("shoppingCartViewModel", shoppingCartViewModel);

        return "cart";
    }

    public String getUser(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("username")) return c.getValue();
            }
        }

        return null;
    }
}
