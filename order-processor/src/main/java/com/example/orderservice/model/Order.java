package com.example.orderservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
public class Order {

    private int orderId;

    private String name;

    private double price;

    private OrderStatus orderStatus;
}
