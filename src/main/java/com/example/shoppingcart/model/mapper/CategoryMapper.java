package com.example.shoppingcart.model.mapper;

import com.example.shoppingcart.entity.Category;
import com.example.shoppingcart.model.dto.CategoryDto;
import com.example.shoppingcart.model.request.view_model.CategoryViewModel;

import java.util.Date;

public class CategoryMapper {

    public static CategoryViewModel toCategoryViewModel(CategoryDto categoryDto) {

        CategoryViewModel categoryViewModel = new CategoryViewModel();
        categoryViewModel.setId(categoryDto.getCategoryId());
        categoryViewModel.setBrand(categoryDto.getName());
        categoryViewModel.setDescription(categoryDto.getDescription());
        categoryViewModel.setCreatedDate(categoryDto.getCreatedDate());

        return categoryViewModel;

    }

    public static CategoryDto toCategoryDto(Category category) {

        CategoryDto tmp = new CategoryDto();

        tmp.setCategoryId(category.getCategoryId());
        tmp.setName(category.getBrand());
        tmp.setDescription(category.getDescription());
        tmp.setCreatedDate(new Date());

        return tmp;
    }

    //Test api
    public static Category toCategory(CategoryDto categoryDto) {

        Category tmp = new Category();

        tmp.setBrand(categoryDto.getName());
        tmp.setDescription(categoryDto.getDescription());
        tmp.setCreatedDate(new Date());

        return tmp;
    }

    public static Category toCategory(CategoryDto categoryDto, int categoryId) {

        Category tmp = new Category();

        tmp.setCategoryId(categoryId);
        tmp.setBrand(categoryDto.getName());
        tmp.setDescription(categoryDto.getDescription());
        tmp.setCreatedDate(new Date());

        return tmp;
    }
}
