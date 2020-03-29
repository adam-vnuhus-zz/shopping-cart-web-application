package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeViewModel {

    private List<ProductViewModel> productViewModels;

    private List<CategoryViewModel> categoryViewModels;

    private String keyWord;
}
