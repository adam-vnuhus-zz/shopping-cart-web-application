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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //Test api
    @ApiOperation(value = "Create product", response = ProductDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Product already exists in the system"),
            @ApiResponse(code = 500, message = "")
    })
    @PostMapping("")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {

        ProductDto result = productService.createProduct(productDto);

        return ResponseEntity.ok(result);
    }

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
