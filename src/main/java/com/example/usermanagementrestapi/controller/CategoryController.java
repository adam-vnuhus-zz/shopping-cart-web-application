package com.example.usermanagementrestapi.controller;

import com.example.usermanagementrestapi.entity.Category;
import com.example.usermanagementrestapi.model.dto.CategoryDto;
import com.example.usermanagementrestapi.model.dto.UserDto;
import com.example.usermanagementrestapi.service.CategoryService;
import com.example.usermanagementrestapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Get list category", response = CategoryDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code=500,message = "")
    })
    @GetMapping("")
    public ResponseEntity<?> getListCategory() {
        List<CategoryDto> result = categoryService.getListCategory();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //Test api
    @ApiOperation(value = "Create category", response = CategoryDto.class)
    @ApiResponses({
            @ApiResponse(code=400,message = "Category already exists in the system"),
            @ApiResponse(code=500,message = "")
    })
    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto result = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
