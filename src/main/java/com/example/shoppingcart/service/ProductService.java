package com.example.shoppingcart.service;

import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.model.dto.ProductDto;
import com.example.shoppingcart.model.request.view_model.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductViewModel> getListProductViewModel(Page<Product> productPage);

    ProductViewModel getProductViewModelByProductId(int id);

    List<ProductViewModel> getListProductViewModel();

    List<ProductDto> getListProduct();

    ProductDto getProductById(int productId);

    List<ProductDto> getListProductNew();

    Product getOne(int productId);

    void deleteProduct(int productId);

    ProductDto updateProduct(ProductDto productDto, int productId);

    Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable,
                                                                  @Param("categoryId") Integer categoryId,
                                                                  @Param("productName") String productName);

    //Test api
    ProductDto createProduct(ProductDto productDto);
}
