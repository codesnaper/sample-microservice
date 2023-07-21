package com.example.productservice.function;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class ProductFunction {
    @Bean
    public Supplier<Message<String>> getAllProduct() {
        Map<String, Object> map = new HashMap<>();
        map.put("s2", "s2");
        MessageHeaders messageHeaders = new MessageHeaders(map);
        return () -> MessageBuilder.createMessage("sample", messageHeaders);

    }

    @Bean
    public Supplier<Message<String>> getAllProduct1() {
        Map<String, Object> map = new HashMap<>();
        map.put("s2", "s2");
        MessageHeaders messageHeaders = new MessageHeaders(map);
        return () -> MessageBuilder.createMessage("p1", messageHeaders);

    }
}
