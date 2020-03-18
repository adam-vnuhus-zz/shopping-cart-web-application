package com.example.usermanagementrestapi.model.mapper;

import com.example.usermanagementrestapi.entity.Category;
import com.example.usermanagementrestapi.model.dto.CategoryDto;

import java.util.Date;

public class CategoryMapper {

    public static CategoryDto toCategoryDto(Category category) {

        CategoryDto tmp = new CategoryDto();
        tmp.setCategoryId(category.getCategoryId());
        tmp.setBrand(category.getBrand());

        return tmp;
    }

    //Test api
    public static Category toCategory(CategoryDto categoryDto){

        Category category = new Category();
        category.setBrand(categoryDto.getBrand());
        category.setCreatedDate(new Date());

        return category;
    }
}
