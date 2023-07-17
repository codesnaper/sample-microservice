package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderEntity> getAllOrder(){
        return this.orderService.getOrder();
    }
}
