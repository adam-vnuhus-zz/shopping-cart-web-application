package com.example.usermanagementrestapi.controller.frontend.client;

import com.example.usermanagementrestapi.entity.CartItem;
import com.example.usermanagementrestapi.entity.Order;
import com.example.usermanagementrestapi.entity.OrderItem;
import com.example.usermanagementrestapi.entity.ShoppingCart;
import com.example.usermanagementrestapi.model.request.view_model.*;
import com.example.usermanagementrestapi.security.CustomUserDetails;
import com.example.usermanagementrestapi.security.JwtUserDetailsService;
import com.example.usermanagementrestapi.service.CartItemService;
import com.example.usermanagementrestapi.service.OrderService;
import com.example.usermanagementrestapi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController extends BaseController {

    @Autowired
    private JwtUserDetailsService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/checkout")
    public String checkout(Model model,
                           @Valid @ModelAttribute("productname") ProductViewModel productName,
                           final Principal principal) {

        OrderViewModel orderViewModel = new OrderViewModel();
        if(principal!= null) {
            String  username = SecurityContextHolder.getContext().getAuthentication().getName();
            CustomUserDetails userEntity = (CustomUserDetails) userService.loadUserByUsername(username);
            if(userEntity!= null) {
                orderViewModel.setAddress(userEntity.getUser().getAddress());
                orderViewModel.setCustomerName(userEntity.getUser().getFullName());
                orderViewModel.setPhoneNumber(userEntity.getUser().getPhone());
                orderViewModel.setEmail(userEntity.getUser().getEmail());
            }
        }

        model.addAttribute("orderViewModel",orderViewModel);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@Valid @ModelAttribute("order") OrderViewModel orderViewModel,
                           @Valid @ModelAttribute("productname") ProductViewModel productName,
                           final Principal principal) {

        Order order = new Order();
        String user = null;

            double totalPrice = 0;

            if(principal != null) {
                String  username = SecurityContextHolder.getContext().getAuthentication().getName();
                order.setUsername(username);
            }

            order.setShipAddress(orderViewModel.getAddress());
            order.setEmail(orderViewModel.getEmail());
            order.setPhoneNumber(orderViewModel.getPhoneNumber());
            order.setCustomerName(orderViewModel.getCustomerName());
            order.setCreatedDate(new Date());


            ShoppingCart cartEntity = shoppingCartService.findByUserName(user);
            if(cartEntity != null) {
                List<OrderItem> orderItems = new ArrayList<>();
                for (CartItem cartItem : cartEntity.getListCartProducts()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setAmount(cartItem.getAmount());

                    double price = cartItem.getAmount() * cartItem.getProduct().getPrice();
                    totalPrice += price;

                    orderItem.setPrice(price);

                    orderItems.add(orderItem);
                }

                order.setListProductOrders(orderItems);
                order.setTotalMoney(totalPrice);

                orderService.addNewOrder(order);

                shoppingCartService.deleteShoppingCart(cartEntity.getShoppingCartId());
            }

        return "redirect:/order/history";
    }

    @GetMapping("/history")
    public String orderHistory(Model model,
                               @Valid @ModelAttribute("productname") ProductViewModel productName,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        OrderHistoryViewModel orderHistoryViewModel = new OrderHistoryViewModel();

        DecimalFormat df = new DecimalFormat("###,###.###");

        List<OrderViewModel> orderViewModels = new ArrayList<>();

        List<Order> orderEntityList = null;

        if(principal != null) {
            String  username = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntityList = orderService.findOrderByGuidOrUsername(null,username);
        }
        if(orderEntityList != null) {
            for(Order order : orderEntityList) {
                OrderViewModel orderViewModel = new OrderViewModel();
                orderViewModel.setId(order.getOrderId());
                orderViewModel.setCustomerName(order.getCustomerName());
                orderViewModel.setEmail(order.getEmail());
                orderViewModel.setAddress(order.getShipAddress());
                orderViewModel.setPhoneNumber(order.getPhoneNumber());
                orderViewModel.setPrice(df.format(order.getTotalMoney()));
                orderViewModel.setCreatedDate(order.getCreatedDate());

                orderViewModels.add(orderViewModel);
            }
        }

        orderHistoryViewModel.setOrderViewModelList(orderViewModels);

        model.addAttribute("orderHistoryViewModel",orderHistoryViewModel);

        return "order-history";
    }

    @GetMapping("/history/{orderId}")
    public String getOrderDetail(Model model,
                                 @Valid @ModelAttribute("productname") ProductViewModel productName,
                                 @PathVariable("orderId") int orderId) {

        OrderDetailViewModel orderDetailViewModel = new OrderDetailViewModel();

        DecimalFormat df = new DecimalFormat("###,###.###");

        double totalPrice = 0;
        int itemAmount = 0;

        List<OrderItemViewModel> orderItemViewModels = new ArrayList<>();

        Order orderEntity = orderService.findOne(orderId);

        if(orderEntity != null) {
            for(OrderItem orderItem : orderEntity.getListProductOrders()) {
                OrderItemViewModel orderItemViewModel = new OrderItemViewModel();

                orderItemViewModel.setProductId(orderItem.getProduct().getProductId());
                orderItemViewModel.setThumbnail(orderItem.getProduct().getThumbnail());
                orderItemViewModel.setAmount(orderItem.getAmount());
                orderItemViewModel.setName(orderItem.getProduct().getName());

                orderItemViewModel.setPrice(df.format(orderItem.getPrice()));

                itemAmount += orderItem.getAmount();
                totalPrice += orderItem.getPrice();

                orderItemViewModels.add(orderItemViewModel);
            }
        }

        orderDetailViewModel.setOrderItemViewModels(orderItemViewModels);
        orderDetailViewModel.setTotalPrice(df.format(totalPrice));
        orderDetailViewModel.setTotalProduct(itemAmount);

        model.addAttribute("orderDetailViewModel",orderDetailViewModel);

        return "order-detail";
    }
}
