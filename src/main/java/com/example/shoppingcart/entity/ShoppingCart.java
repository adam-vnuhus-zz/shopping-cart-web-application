package com.example.shoppingcart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingCart {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int shoppingCartId;

    private String guid;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private List<CartItem> listCartProducts = new ArrayList<>();
}
