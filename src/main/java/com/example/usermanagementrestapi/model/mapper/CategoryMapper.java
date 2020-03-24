package com.example.usermanagementrestapi.model.mapper;

import com.example.usermanagementrestapi.entity.Category;
import com.example.usermanagementrestapi.model.dto.CategoryDto;

import java.util.Date;

public class CategoryMapper {

    public static CategoryDto toCategoryDto(Category category) {

        CategoryDto tmp = new CategoryDto();

        tmp.setCategoryId(category.getCategoryId());
        tmp.setName(category.getBrand());
        tmp.setDescription(category.getDescription());
        tmp.setCreatedDate(new Date());

        return tmp;
    }

    //Test api
    public static Category toCategory(CategoryDto categoryDto){

        Category tmp = new Category();

        tmp.setBrand(categoryDto.getName());
        tmp.setDescription(categoryDto.getDescription());
        tmp.setCreatedDate(new Date());

        return tmp;
    }

    public static Category toCategory(CategoryDto categoryDto,int categoryId){

        Category tmp = new Category();

        tmp.setCategoryId(categoryId);
        tmp.setBrand(categoryDto.getName());
        tmp.setDescription(categoryDto.getDescription());
        tmp.setCreatedDate(new Date());

        return tmp;
    }
}
