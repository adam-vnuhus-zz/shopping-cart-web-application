package com.example.usermanagementrestapi.model.mapper;

import com.example.usermanagementrestapi.entity.Category;
import com.example.usermanagementrestapi.model.dto.CategoryDto;

public class CategoryMapper {

    public static CategoryDto toCategoryDto(Category category) {

        CategoryDto tmp = new CategoryDto();
        tmp.setCategoryId(category.getCategory_id());
        tmp.setBrand(category.getBrand());

        return tmp;
    }
}
