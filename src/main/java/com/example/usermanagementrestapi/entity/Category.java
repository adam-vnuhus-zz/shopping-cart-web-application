package com.example.usermanagementrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    //name
    @Column(name = "brand")
    private String brand;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
