package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.entity.OrderItem;
import com.example.shoppingcart.entity.ShoppingCart;
import com.example.shoppingcart.model.mapper.OrderMapper;
import com.example.shoppingcart.model.request.view_model.OrderItemViewModel;
import com.example.shoppingcart.model.request.view_model.OrderViewModel;
import com.example.shoppingcart.repository.OrderRepository;
import com.example.shoppingcart.security.CustomUserDetails;
import com.example.shoppingcart.security.JwtUserDetailsService;
import com.example.shoppingcart.service.OrderService;
import com.example.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private JwtUserDetailsService userService;

    @Override
    public void getOrderByOrderViewModel(OrderViewModel orderViewModel, Principal principal) {
        Order order = new Order();

        double totalPrice = 0;

        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            order.setUsername(username);
        }

        order.setShipAddress(orderViewModel.getAddress());
        order.setEmail(orderViewModel.getEmail());
        order.setPhoneNumber(orderViewModel.getPhoneNumber());
        order.setCustomerName(orderViewModel.getCustomerName());
        order.setCreatedDate(new Date());


        ShoppingCart cartEntity = shoppingCartService.findByUserName(order.getUsername());
        if (cartEntity != null) {
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
    }

    @Override
    public int getTotalItemsListOrderItemViewModelByOrderId(int id) {
        int totalItems = 0;
        Order orderEntity = orderService.findOne(id);
        if (orderEntity != null) {
            for (OrderItem orderItem : orderEntity.getListProductOrders()) {
                totalItems += orderItem.getAmount();
            }
        }
        return totalItems;
    }

    @Override
    public double getTotalPriceListOrderItemViewModelByOrderId(int id) {

        double totalPrice = 0;

        Order orderEntity = orderService.findOne(id);
        if (orderEntity != null) {
            for (OrderItem orderItem : orderEntity.getListProductOrders()) {
                totalPrice += orderItem.getPrice();
            }
        }
        return totalPrice;
    }

    @Override
    public List<OrderItemViewModel> getListOrderItemViewModelByOrderId(int id) {

        DecimalFormat df = new DecimalFormat("###,###.###");

        List<OrderItemViewModel> orderItemViewModels = new ArrayList<>();

        Order orderEntity = orderService.findOne(id);
        if (orderEntity != null) {
            for (OrderItem orderItem : orderEntity.getListProductOrders()) {
                OrderItemViewModel orderItemViewModel = new OrderItemViewModel();

                orderItemViewModel.setProductId(orderItem.getProduct().getProductId());
                orderItemViewModel.setThumbnail(orderItem.getProduct().getThumbnail());
                orderItemViewModel.setAmount(orderItem.getAmount());
                orderItemViewModel.setName(orderItem.getProduct().getName());

                orderItemViewModel.setPrice(df.format(orderItem.getPrice()));

                orderItemViewModels.add(orderItemViewModel);
            }
        }
        return orderItemViewModels;
    }

    @Override
    public OrderViewModel getOrderViewModel() {

        OrderViewModel orderViewModel = new OrderViewModel();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        CustomUserDetails userEntity = (CustomUserDetails) userService.loadUserByUsername(username);

        if (userEntity != null) {
            orderViewModel.setAddress(userEntity.getUser().getAddress());
            orderViewModel.setCustomerName(userEntity.getUser().getFullName());
            orderViewModel.setPhoneNumber(userEntity.getUser().getPhone());
            orderViewModel.setEmail(userEntity.getUser().getEmail());
        }

        return orderViewModel;

    }

    @Override
    public List<OrderViewModel> getListOrderViewModel(List<Order> orderEntityList) {
        List<OrderViewModel> orderViewModels = new ArrayList<>();

        for (Order order : orderEntityList) {

            orderViewModels.add(OrderMapper.toOrderViewModel(order));
        }

        return orderViewModels;
    }

    @Override
    public List<Order> getListOrder() {

        return orderRepository.findAll();
    }

    @Override
    public void addNewOrder(Order order) {

        orderRepository.save(order);
    }

    @Override
    public Order findOne(int orderId) {

        return orderRepository.getOne(orderId);
    }

    @Override
    public List<Order> findOrderByGuidOrUsername(String guid, String username) {
        return orderRepository.findOrderByGuidOrUsername(guid, username);
    }
}
