package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ShoppingCartViewModel {

    private int productAmount;

    private String totalPrice;

    private List<CartItemViewModel> cartItemViewModels;

}
