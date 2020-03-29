package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Category;
import com.example.shoppingcart.model.dto.CategoryDto;
import com.example.shoppingcart.model.request.view_model.CategoryViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryViewModel> getListCategoryViewModel();

    List<CategoryDto> getListCategory();

    //Test api
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(int categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

    void deleteCategory(int categoryId);

    Category getOne(int categoryId);

    Page<Category> getListCategoryByCategoryNameContaining(Pageable pageable, String categoryName);
}
