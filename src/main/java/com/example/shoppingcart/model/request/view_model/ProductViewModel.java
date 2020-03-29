package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//product view model
public class ProductViewModel {

    private int id;

    private String categoryBrand;

    private String name;

    private String description;

    private String thumbnail;

    private double price;

    private Date createdDate;

    private List<ProductViewModel> productViewModels;

    private List<CategoryViewModel> categoryViewModels;

    private List<ImageViewModel> imageViewModels;

    private String keyWord;
}
