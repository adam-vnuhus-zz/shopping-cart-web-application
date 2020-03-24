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
@Table(name = "product")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "name", nullable = false)
    private String name;

    //Main image
    @Column(name = "thumbnail")
    private String thumbnail;

    @Type(type = "json")
    @Column(name = "engine", columnDefinition = "json")
    private Engine engine;

    private String description;

    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @Column(name = "stock_amount")
    private int stockAmount;

    private double price;
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Images> images;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Engine implements Serializable {
        private int cc;

        private String type;
    }

}
