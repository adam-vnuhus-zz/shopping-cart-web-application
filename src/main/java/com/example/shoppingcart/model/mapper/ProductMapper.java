package com.example.shoppingcart.model.mapper;

import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.model.dto.ProductDto;
import com.example.shoppingcart.model.request.view_model.ProductViewModel;

import java.util.Date;

public class ProductMapper {

    public static ProductViewModel toProductViewModel(Product product) {

        ProductViewModel temp = new ProductViewModel();

        temp.setId(product.getProductId());
        temp.setName(product.getName());
        temp.setThumbnail(product.getThumbnail());
        temp.setDescription(product.getDescription());
        temp.setPrice(product.getPrice());

        return temp;
    }

    public static ProductViewModel toProductViewModel(ProductDto productDto) {

        ProductViewModel temp = new ProductViewModel();

        temp.setId(productDto.getProductId());
        temp.setName(productDto.getName());
        temp.setThumbnail(productDto.getThumbnail());
        temp.setPrice(productDto.getPrice());
        temp.setDescription(productDto.getDescription());
        temp.setCreatedDate(productDto.getCreatedDate());

        return temp;
    }

    public static ProductDto toProductDto(Product product) {

        ProductDto tmp = new ProductDto();

        tmp.setProductId(product.getProductId());
        tmp.setName(product.getName());
        tmp.setDescription(product.getDescription());
        //Image
        tmp.setThumbnail(product.getThumbnail());
        tmp.setPrice(product.getPrice());
        tmp.setCreatedDate(new Date());
        tmp.setCategoryId(product.getCategory().getCategoryId());

        return tmp;
    }

    //Test api
    public static Product toProduct(ProductDto productDto) {

        Product tmp = new Product();

        tmp.setName(productDto.getName());
        tmp.setDescription(productDto.getDescription());
        tmp.setPrice(productDto.getPrice());
        tmp.setThumbnail(productDto.getThumbnail());
        tmp.setCreateDate(new Date());

        return tmp;
    }

    public static Product toProduct(ProductDto productDto, int productId) {

        Product tmp = new Product();

        tmp.setProductId(productId);
        tmp.setName(productDto.getName());
        tmp.setDescription(productDto.getDescription());
        tmp.setPrice(productDto.getPrice());
        tmp.setThumbnail(productDto.getThumbnail());
        tmp.setCreateDate(new Date());
        return tmp;
    }
}
