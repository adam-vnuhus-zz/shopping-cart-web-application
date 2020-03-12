package com.example.usermanagementrestapi.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Type(type = "json")
    @Column(name = "images", columnDefinition = "json")
    private ArrayList<String> images;

    @Type(type = "json")
    @Column(name = "engine", columnDefinition = "json")
    private Engine engine;

    @Column(name = "stock_amount")
    private int stockAmount;

    @Column(name = "price")
    private float price;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Engine implements Serializable {
        private int cc;

        private String type;
    }

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
