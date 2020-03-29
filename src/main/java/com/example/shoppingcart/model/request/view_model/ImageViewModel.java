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
public class ImageViewModel {

    private int id;

    private String link;

    private Date createdDate;

    private List<ImageViewModel> imageViewModels;

    private int productId;
}
