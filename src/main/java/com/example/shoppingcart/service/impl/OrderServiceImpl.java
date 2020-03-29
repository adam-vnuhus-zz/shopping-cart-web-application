package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.repository.OrderRepository;
import com.example.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
