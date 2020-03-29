package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.exception.DuplicateRecordException;
import com.example.shoppingcart.exception.InternalServerException;
import com.example.shoppingcart.exception.NotFoundException;
import com.example.shoppingcart.model.dto.ProductDto;
import com.example.shoppingcart.model.mapper.ProductMapper;
import com.example.shoppingcart.repository.CategoryRepository;
import com.example.shoppingcart.repository.ProductRepository;
import com.example.shoppingcart.service.ProductService;
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
        List<ProductDto> rs = new ArrayList<>();

        for (Product product : products) {
            rs.add(ProductMapper.toProductDto(product));
        }

        return rs;
    }

    @Override
    public ProductDto getProductById(int productId) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("Không tìm thấy sản phẩm");
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

        return productRepository.getOne(productId);
    }

    @Override
    public void deleteProduct(int productId) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("No product found");
        }
        try {
            productRepository.deleteById(productId);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't delete product");
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, int productId) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new NotFoundException("No product found");
        }

        Product updateProduct = ProductMapper.toProduct(productDto, productId);
        updateProduct.setCategory(categoryRepository.getOne(productDto.getCategoryId()));

        try {
            productRepository.save(updateProduct);
        } catch (Exception e) {
            throw new InternalServerException("Database error. Can't update product");
        }

        return ProductMapper.toProductDto(updateProduct);
    }

    @Override
    public Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable, Integer categoryId, String productName) {
        return productRepository.getListProductByCategoryOrProductNameContaining(pageable, categoryId, productName);
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
