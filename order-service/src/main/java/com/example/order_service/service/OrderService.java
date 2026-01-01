package com.example.order_service.service;

import com.example.order_service.dto.ResponseMessage;
import com.example.order_service.entity.Order;
import com.example.order_service.exception.InValidOrderUpdateException;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.repository.OrderRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderService implements OrderServiceInterface{
    @Autowired
    OrderRepositoryInterface orderRepo;

    @Override
    public ResponseMessage createOrderService(Order order) {
        orderRepo.save(order);
        return new ResponseMessage("Order placed successfully");
    }

    @Override
    public List<Order> getAllOrdersService() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrdersService(int id) {
        return orderRepo.findById(id).orElseThrow(()->new OrderNotFoundException("order doesn't exist with order id :"+id));
    }

    @Override
    public ResponseMessage updateOrderService(int id, Order newOrder) {
        Order existingOrder = orderRepo.findById(id).orElseThrow(()->new OrderNotFoundException("order doesn't exist with order id :"+id));
        if(existingOrder.getOrderId()!= newOrder.getOrderId() && newOrder.getOrderId()!=0){
            throw new InValidOrderUpdateException("Updating order ID is not allowed");
        }
        existingOrder.setItemName(newOrder.getItemName());
        existingOrder.setPrice(newOrder.getPrice());
        orderRepo.save(existingOrder);
        return new ResponseMessage("Order details updated successfully");
    }

    @Override
    public ResponseMessage deleteOrderService(int id) {
        if(!orderRepo.existsById(id))
            throw new OrderNotFoundException("order doesn't exist with order id :"+id);
        orderRepo.deleteById(id);
        return new ResponseMessage("Order deleted successfully");
    }
}
