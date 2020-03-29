package com.example.shoppingcart.model.dto;

import com.example.shoppingcart.extension.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private int categoryId;

    private String name;

    private String description;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdDate;
}
