package com.example.order_service.service;

import com.example.order_service.dto.ResponseMessage;
import com.example.order_service.entity.Order;

import java.util.List;

public interface OrderServiceInterface {

    ResponseMessage createOrderService(Order order);

    List<Order> getAllOrdersService();

    Order getOrdersService(int id);

    ResponseMessage updateOrderService(int id, Order order);

    ResponseMessage deleteOrderService(int id);
}
