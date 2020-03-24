package com.example.usermanagementrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "guid")
    private String guid;

    @Column(name = "username")
    private String username;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "status")
    private int status;

    @Column(name = "ship_address")
    private String shipAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "total_money")
    private double totalMoney;

    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> listProductOrders = new ArrayList<>();
}
