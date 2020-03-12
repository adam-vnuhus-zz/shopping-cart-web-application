package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService  {

    public List<ProductDto> getListProduct();

    public ProductDto getProductById(int id);
}
