package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryViewModel {

    private int id;

    private String brand;

    private String description;

    private Date createdDate;

    private List<CategoryViewModel> categoryViewModels;

    private String keyWord;
}
