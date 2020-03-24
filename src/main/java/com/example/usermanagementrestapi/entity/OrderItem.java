package com.example.usermanagementrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
//OrderProduct
public class OrderItem implements Serializable {

    //orderProductId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private int orderItemId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private double price;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
