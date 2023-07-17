package com.example.productservice.processor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class ProductFunction {

    public Supplier<Message<String>> getAllProduct(){
        Map<String, Object> map = new HashMap<>();
        map.put("s2","s2");
        MessageHeaders messageHeaders = new MessageHeaders(map);
        return () -> MessageBuilder.createMessage("sample", messageHeaders);

    }
}
