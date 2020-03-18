package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.Product;
import com.example.usermanagementrestapi.exception.DuplicateRecordException;
import com.example.usermanagementrestapi.exception.NotFoundException;
import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.mapper.ProductMapper;
import com.example.usermanagementrestapi.repository.CategoryRepository;
import com.example.usermanagementrestapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Override
    public List<ProductDto> getListProductNew() {

        List<Product> products = productRepository.getListProductNew();
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(ProductMapper.toProductDto(product));
        }
        return productDtos;
    }

    @Override
    public Product getOne(int productId) {

        Product product = productRepository.getOne(productId);

        return product;
    }

    @Override
    public Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable, Integer categoryId, String productName) {
        return null;
    }

    //Test api

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product product = productRepository.findAllByName(productDto.getName());
        if (product != null) {
            throw new DuplicateRecordException("Ten san pham da ton tai");
        }

        product = ProductMapper.toProduct(productDto);
        product.setCategory(categoryRepository.getOne(productDto.getCategoryId()));
        productRepository.save(product);
        return ProductMapper.toProductDto(product);
    }
}
