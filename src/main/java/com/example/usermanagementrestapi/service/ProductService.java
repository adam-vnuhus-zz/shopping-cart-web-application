package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.Product;
import com.example.usermanagementrestapi.model.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService  {

    public List<ProductDto> getListProduct();

    public ProductDto getProductById(int id);

    public List<ProductDto> getListProductNew();

    public Product getOne(int productId);

    public Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable,
                                                                         @Param("categoryId") Integer categoryId,
                                                                         @Param("productName") String productName);
}
