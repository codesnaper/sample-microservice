package com.example.orderservice.entity;

import com.example.orderservice.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "sample_order_t")
@ToString
@Data
public class OrderEntity {

    @Id
    private int id;

    @Column(name = "product_id")
    private int productId;

    @CreatedDate
    private Date createdDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int txnId;

    private int customerId;

}
