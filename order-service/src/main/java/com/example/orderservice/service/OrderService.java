package com.example.orderservice.service;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getOrder(){
        return this.orderRepository.findAll();

    }
}
