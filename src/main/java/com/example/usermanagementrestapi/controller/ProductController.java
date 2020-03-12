package com.example.usermanagementrestapi.controller;

import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.dto.UserDto;
import com.example.usermanagementrestapi.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Get list product", response = ProductDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code=500,message = "")
    })
    @GetMapping("")
    public ResponseEntity<?> getListProduct() {
        List<ProductDto> result = productService.getListProduct();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "Get product info by id", response = ProductDto.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No product found"),
            @ApiResponse(code=500,message = "")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        ProductDto result = productService.getProductById(id);
        return ResponseEntity.ok(result);
    }
}
