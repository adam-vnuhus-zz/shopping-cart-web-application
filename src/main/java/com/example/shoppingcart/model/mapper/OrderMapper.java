package com.example.shoppingcart.model.mapper;

import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.model.request.view_model.OrderViewModel;

import java.text.DecimalFormat;

public class OrderMapper {

    public static OrderViewModel toOrderViewModel(Order order) {

        DecimalFormat df = new DecimalFormat("###,###.###");

        OrderViewModel orderViewModel = new OrderViewModel();

        orderViewModel.setId(order.getOrderId());
        orderViewModel.setCustomerName(order.getCustomerName());
        orderViewModel.setEmail(order.getEmail());
        orderViewModel.setAddress(order.getShipAddress());
        orderViewModel.setPhoneNumber(order.getPhoneNumber());
        orderViewModel.setPrice(df.format(order.getTotalMoney()));
        orderViewModel.setCreatedDate(order.getCreatedDate());

        return orderViewModel;

    }
}
