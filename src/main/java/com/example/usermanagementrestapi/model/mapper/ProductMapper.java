package com.example.usermanagementrestapi.model.mapper;

import com.example.usermanagementrestapi.entity.Product;
import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.dto.UserDto;

import java.util.Date;

public class ProductMapper {

    public static ProductDto toProductDto(Product product) {

        ProductDto tmp = new ProductDto();
        tmp.setProductId(product.getProductId());
        tmp.setName(product.getName());
        tmp.setThumbnail(product.getThumbnail());
        tmp.setPrice(product.getPrice());

        return tmp;
    }

    //Test api
    public static Product toProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setThumbnail(productDto.getThumbnail());
        product.setCreateDate(new Date());
        return product;
    }
}
