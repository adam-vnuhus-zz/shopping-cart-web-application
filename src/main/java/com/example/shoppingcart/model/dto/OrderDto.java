package com.example.shoppingcart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int orderId;

    private int status;

    private String shipAddress;

    private float totalMoney;
}
