package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> getListOrder();

    void addNewOrder(Order order);

    Order findOne(int orderId);

    List<Order> findOrderByGuidOrUsername(String guid, String username);
}
