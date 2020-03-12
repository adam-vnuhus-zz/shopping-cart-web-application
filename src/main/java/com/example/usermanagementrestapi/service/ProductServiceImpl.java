package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.Product;
import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.exception.NotFoundException;
import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.dto.UserDto;
import com.example.usermanagementrestapi.model.mapper.ProductMapper;
import com.example.usermanagementrestapi.model.mapper.UserMapper;
import com.example.usermanagementrestapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getListProduct() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> rs = new ArrayList<ProductDto>();
        for (Product product : products) {
            rs.add(ProductMapper.toProductDto(product));
        }
        return rs;
    }

    @Override
    public ProductDto getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("No product found");
        }

        return ProductMapper.toProductDto(product.get());
    }
}
