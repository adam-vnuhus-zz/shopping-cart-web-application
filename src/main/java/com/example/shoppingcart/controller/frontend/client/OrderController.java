package com.example.shoppingcart.controller.frontend.client;

import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.model.request.view_model.OrderDetailViewModel;
import com.example.shoppingcart.model.request.view_model.OrderHistoryViewModel;
import com.example.shoppingcart.model.request.view_model.OrderViewModel;
import com.example.shoppingcart.security.JwtUserDetailsService;
import com.example.shoppingcart.service.OrderService;
import com.example.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtUserDetailsService userService;

    @GetMapping("/history")
    public String orderHistory(Model model,
                               final Principal principal) {

        OrderHistoryViewModel orderHistoryViewModel = new OrderHistoryViewModel();

        List<Order> orderEntityList = null;

        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntityList = orderService.findOrderByGuidOrUsername(null, username);
        }
        if (orderEntityList != null) {
            orderHistoryViewModel.setOrderViewModelList(orderService.getListOrderViewModel(orderEntityList));
        }

        model.addAttribute("orderHistoryViewModel", orderHistoryViewModel);

        return "order-history";

    }

    @GetMapping("/checkout")
    public String checkout(Model model,
                           final Principal principal) {

        OrderViewModel orderViewModel = new OrderViewModel();

        if (principal != null) {
            orderViewModel = orderService.getOrderViewModel();
        }

        model.addAttribute("orderViewModel", orderViewModel);
        return "checkout";
    }

    @GetMapping("/history/{orderId}")
    public String getOrderDetail(Model model,
                                 @PathVariable("orderId") int orderId) {

        OrderDetailViewModel orderDetailViewModel = new OrderDetailViewModel();

        DecimalFormat df = new DecimalFormat("###,###.###");

        orderDetailViewModel.setOrderItemViewModels(orderService.getListOrderItemViewModelByOrderId(orderId));
        orderDetailViewModel.setTotalPrice(df.format(orderService.getTotalPriceListOrderItemViewModelByOrderId(orderId)));
        orderDetailViewModel.setTotalProduct(orderService.getTotalItemsListOrderItemViewModelByOrderId(orderId));

        model.addAttribute("orderDetailViewModel", orderDetailViewModel);

        return "order-detail";
    }

    @PostMapping("/checkout")
    public String checkout(@Valid @ModelAttribute("order") OrderViewModel orderViewModel,
                           final Principal principal) {

        orderService.getOrderByOrderViewModel(orderViewModel, principal);

        return "redirect:/order/history";
    }
}
