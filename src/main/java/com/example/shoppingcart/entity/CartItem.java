package com.example.shoppingcart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "cart_item_id")
    private int cartItemId;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "shopping_cart_id", insertable = false, updatable = false)
    private int shoppingCardId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
