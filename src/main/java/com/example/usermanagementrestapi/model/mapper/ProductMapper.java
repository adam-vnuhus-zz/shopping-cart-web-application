package com.example.usermanagementrestapi.model.mapper;

import com.example.usermanagementrestapi.entity.Product;
import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.dto.UserDto;

public class ProductMapper {

    public static ProductDto toProductDto(Product product) {

        ProductDto tmp = new ProductDto();
        tmp.setProductId(product.getProductId());
        tmp.setName(product.getName());
        tmp.setThumbnail(product.getThumbnail());
        tmp.setPrice(product.getPrice());

        return tmp;
    }
}
