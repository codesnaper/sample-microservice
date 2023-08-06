//package com.example.orderservice.function;
//
//import com.example.orderservice.model.Order;
//import com.example.orderservice.model.OrderStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.function.context.PollableBean;
//import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.config.GlobalChannelInterceptor;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.stereotype.Service;
//import reactor.util.function.Tuple2;
//
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.function.Function;
//import java.util.function.Supplier;
//
//@Service
//public class OrderProcessor {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessor.class);
//
//
//    @Bean
//    @GlobalChannelInterceptor(patterns = "processOrder-*")
//    public ChannelInterceptor customInterceptor() {
//        return new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                LOGGER.info("Message > {}", message );
//                return ChannelInterceptor.super.preSend(message, channel);
//            }
//        };
//    }
//
//
//    @Bean
//    public Function<Tuple2<Order, Order>, Order> mergeOrder() {
//        return tuple -> {
//            Order order1 =  tuple.getT1();
//            Order order2 = tuple.getT2();
//            order1.setName(order1.getName() + ";"+ order2.getName());
//            order1.setPrice(order1.getPrice() + order2.getPrice());
//            return order1;
//        };
//    }
//
//    @Bean
//    public Function<Order, Order> processOrder() {
//        return order -> {
//            order.setOrderStatus(OrderStatus.PROCESS);
//            LOGGER.info("Order Completed>> {}", order);
//            return order;
//        };
//    }
//
//    @Bean
//    @PollableBean
//    public Supplier<Order> addOrder(){
//        return () -> {
//            Order order = new Order();
//            order.setName(new Random().toString());
//            order.setOrderId(new Random().nextInt());
//            order.setOrderStatus(OrderStatus.CREATED);
//            order.setPrice(new Random().nextDouble());
//            LOGGER.info("Order created > {}", order);
//            return order;
//        };
//    }
//}
