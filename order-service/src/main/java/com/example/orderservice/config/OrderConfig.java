package com.example.orderservice.config;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@ToString
public class OrderConfig {

    @Value("${order.name}")
    private String name;

}
