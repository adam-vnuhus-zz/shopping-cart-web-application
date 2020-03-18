package com.example.usermanagementrestapi.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Type(type = "json")
    @Column(name = "engine", columnDefinition = "json")
    private Engine engine;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date", updatable = false)
    private Date createDate;

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

    @OneToMany(mappedBy = "product")
    private List<Images> images;

}
