//package com.example.orderservice.controller;
//
//import com.example.orderservice.model.Order;
//import com.example.orderservice.model.OrderStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@RestController
//public class OrderController {
//
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
//
//    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);
//
//    private final StreamBridge streamBridge;
//
//    public OrderController(StreamBridge streamBridge) {
//        this.streamBridge = streamBridge;
//    }
//
//    @GetMapping( value = "/neworder", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public Order delegateToSupplier() {
//        LOGGER.info("Creating new order");
//        Order order = new Order();
//        order.setOrderId(ATOMIC_INTEGER.getAndIncrement());
//        order.setName(new Random().toString());
//        order.setOrderStatus(OrderStatus.CREATED);
//        order.setPrice(new Random().nextDouble());
//        Message<Order> orderMessage = MessageBuilder.withPayload(order).setHeaderIfAbsent("orderId", order.getOrderId()).build();
//        streamBridge.send("processOrder-in-0", orderMessage, MediaType.APPLICATION_JSON);
////        streamBridge.send("mergeOrder-in-1", orderMessage, MediaType.APPLICATION_JSON);
////        streamBridge.send("mergeOrder-in-0", orderMessage, MediaType.APPLICATION_JSON);
//        return order;
//    }
//}
