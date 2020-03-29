package com.example.shoppingcart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CartItemDto {

    private int id;

    private int productId;

    private String guid;

    private String username;

    private int amount;
}
