package com.example.usermanagementrestapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status")
    private int status;

    @CreationTimestamp
    @Column(name = "createddate" ,updatable = false)
    private Date createdDate;

    @Column(name="total_money")
    private float totalMoney;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
