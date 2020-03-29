package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemViewModel {

    private int id;

    private int productId;

    private String thumbnail;

    private int amount;

    private String productName;

    private String price;

    private String totalPrice;

}
