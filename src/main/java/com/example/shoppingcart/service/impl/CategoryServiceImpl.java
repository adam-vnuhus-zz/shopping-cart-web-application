package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Category;
import com.example.shoppingcart.exception.DuplicateRecordException;
import com.example.shoppingcart.exception.InternalServerException;
import com.example.shoppingcart.exception.NotFoundException;
import com.example.shoppingcart.model.dto.CategoryDto;
import com.example.shoppingcart.model.mapper.CategoryMapper;
import com.example.shoppingcart.model.request.view_model.CategoryViewModel;
import com.example.shoppingcart.repository.CategoryRepository;
import com.example.shoppingcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryViewModel> getListCategoryViewModel() {
        List<CategoryDto> categoryDtos = getListCategory();
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtos) {

            categoryViewModels.add(CategoryMapper.toCategoryViewModel(categoryDto));
        }

        return categoryViewModels;
    }

    @Override
    public List<CategoryDto> getListCategory() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDto> rs = new ArrayList<>();
        for (Category category : categories) {
            rs.add(CategoryMapper.toCategoryDto(category));
        }

        return rs;
    }

    //Test api
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = categoryRepository.findAllByBrand(categoryDto.getName());
        if (category != null) {
            throw new DuplicateRecordException("Ten danh muc da ton tai");
        }

        category = CategoryMapper.toCategory(categoryDto);
        categoryRepository.save(category);

        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("No category found");
        }

        return CategoryMapper.toCategoryDto(category.get());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("No category found");
        }
        Category updateCategory = CategoryMapper.toCategory(categoryDto, categoryId);
        try {
            categoryRepository.save(updateCategory);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't update category");
        }

        return CategoryMapper.toCategoryDto(updateCategory);
    }

    @Override
    public void deleteCategory(int categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("No category found");
        }
        try {
            categoryRepository.deleteById(categoryId);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't delete category");
        }
    }

    @Override
    public Category getOne(int categoryId) {

        return categoryRepository.getOne(categoryId);
    }

    @Override
    public Page<Category> getListCategoryByCategoryNameContaining(Pageable pageable, String categoryName) {

        return categoryRepository.getListCategoryByCategoryNameContaining(pageable, categoryName);
    }
}
