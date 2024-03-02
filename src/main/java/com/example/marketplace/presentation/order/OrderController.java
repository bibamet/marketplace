package com.example.marketplace.presentation.order;

import com.example.marketplace.application.order.OrderService;
import com.example.marketplace.presentation.order.dto.commands.CreateOrderCommand;
import com.example.marketplace.presentation.order.dto.commands.UpdateOrderCommand;
import com.example.marketplace.presentation.order.dto.queries.OrderQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderQuery> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{orderId}")
    public OrderQuery getById(@PathVariable Integer orderId) {
        return orderService.getByID(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<OrderQuery> getOrderHistory(@PathVariable Integer userId) {
        return orderService.getUsersOrderHistory(userId);
    }

    @PutMapping
    public OrderQuery changeStatus(@RequestBody UpdateOrderCommand updateOrderCommand) {
        return orderService.updateStatus(updateOrderCommand);
    }

    @PostMapping
    public OrderQuery createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        return orderService.createOrder(createOrderCommand);
    }

}
