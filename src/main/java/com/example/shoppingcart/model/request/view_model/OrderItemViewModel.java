package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderItemViewModel {

    private int productId;

    private String thumbnail;

    private int amount;

    private String price;

    private String name;
}
