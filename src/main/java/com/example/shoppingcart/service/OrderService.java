package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> getListOrder();

    void addNewOrder(Order order);

    Order findOne(int orderId);

    List<Order> findOrderByGuidOrUsername(String guid, String username);
}
