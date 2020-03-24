package com.example.usermanagementrestapi.entity;

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
@Table(name = "shopping_cart")
public class ShoppingCart {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "shopping_cart_id")
    private int shoppingCartId;

    @Column(name = "guid")
    private String guid;

    @Column(name = "username")
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopping_cart")
    private List<CartItem> listCartProducts = new ArrayList<>();
}
