package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.Category;
import com.example.usermanagementrestapi.model.dto.CategoryDto;
import com.example.usermanagementrestapi.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public List<CategoryDto> getListCategory();

    //Test api
    CategoryDto createCategory(CategoryDto categoryDto);
}
