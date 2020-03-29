package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Order;
import com.example.shoppingcart.model.request.view_model.OrderItemViewModel;
import com.example.shoppingcart.model.request.view_model.OrderViewModel;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface OrderService {

    void getOrderByOrderViewModel(OrderViewModel orderViewModel, Principal principal);

    int getTotalItemsListOrderItemViewModelByOrderId(int id);

    double getTotalPriceListOrderItemViewModelByOrderId(int id);

    List<OrderItemViewModel> getListOrderItemViewModelByOrderId(int id);

    OrderViewModel getOrderViewModel();

    List<OrderViewModel> getListOrderViewModel(List<Order> order);

    List<Order> getListOrder();

    void addNewOrder(Order order);

    Order findOne(int orderId);

    List<Order> findOrderByGuidOrUsername(String guid, String username);
}
