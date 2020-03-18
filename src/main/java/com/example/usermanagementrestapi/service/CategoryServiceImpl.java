package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.Category;
import com.example.usermanagementrestapi.exception.DuplicateRecordException;
import com.example.usermanagementrestapi.model.dto.CategoryDto;
import com.example.usermanagementrestapi.model.mapper.CategoryMapper;
import com.example.usermanagementrestapi.repository.CategoryRepository;
import com.example.usermanagementrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getListCategory() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDto> rs = new ArrayList<CategoryDto>();
        for (Category category: categories) {
            rs.add(CategoryMapper.toCategoryDto(category));
        }

        return rs;
    }

    //Test api
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = categoryRepository.findAllByBrand(categoryDto.getBrand());
        if (category != null){
            throw new DuplicateRecordException("Ten danh muc da ton tai");
        }

        category = CategoryMapper.toCategory(categoryDto);
        categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(category);
    }
}
