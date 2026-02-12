package com.example.order_service.controller;

import com.example.order_service.dto.OrderResponse;
import com.example.order_service.dto.ResponseMessage;
import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    OrderServiceInterface orderService;

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order with item details and price"
    )
    @PostMapping
    public ResponseMessage createOrder(@RequestBody Order order) {
        return orderService.createOrderService(order);
    }

    @GetMapping
    public List<Order> retrieveAllOrders() {
        return orderService.getAllOrdersService();
    }

    @GetMapping("/{orderId}")
    public OrderResponse retrieveOrder(@PathVariable("orderId") int id) {
        Order order = orderService.getOrdersService(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getOrderId());
        orderResponse.setItem(order.getItemName());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setInstance(instanceId);
        return orderResponse;
    }

    @PutMapping("/{orderId}")
    public ResponseMessage updateOrder(@PathVariable("orderId") int id, @RequestBody Order order) {
        return orderService.updateOrderService(id, order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseMessage deleteOrder(@PathVariable("orderId") int id) {
        return orderService.deleteOrderService(id);
    }
}
