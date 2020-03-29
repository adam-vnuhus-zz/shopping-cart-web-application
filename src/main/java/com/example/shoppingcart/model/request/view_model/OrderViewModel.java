package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderViewModel {

    private int id;

    private String customerName;

    private String phoneNumber;

    private String address;

    private String email;

    private Date createdDate;

    private String price;
}
