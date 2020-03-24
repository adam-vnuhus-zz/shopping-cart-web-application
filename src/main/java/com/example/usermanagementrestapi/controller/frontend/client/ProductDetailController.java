package com.example.usermanagementrestapi.controller.frontend.client;


import com.example.usermanagementrestapi.entity.Images;
import com.example.usermanagementrestapi.entity.Product;
import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.request.view_model.ImageViewModel;
import com.example.usermanagementrestapi.model.request.view_model.ProductDetailViewModel;
import com.example.usermanagementrestapi.model.request.view_model.ProductViewModel;
import com.example.usermanagementrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Integer productId,
                                Model model,
                                @Valid @ModelAttribute("productname") ProductViewModel productName){

        ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel();
        Product product = productService.getOne(productId);
        ProductViewModel productViewModel = new ProductViewModel();
        if(product!=null) {
            productViewModel.setId(product.getProductId());
            productViewModel.setName(product.getName());
            productViewModel.setThumbnail(product.getThumbnail());
            productViewModel.setDescription(product.getDescription());
            productViewModel.setPrice(product.getPrice());

            /**
             * set list product image vm
             */
            List<ImageViewModel> imageViewModels = new ArrayList<>();
            for(Images images : product.getImages()) {
                ImageViewModel imageViewModel = new ImageViewModel();
                imageViewModel.setLink(images.getLink());

                imageViewModels.add(imageViewModel);
            }

            productViewModel.setImageViewModels(imageViewModels);
        }

        List<ProductDto> productDtos = productService.getListProductNew();
        List<ProductViewModel> productViewModels = new ArrayList<>();

        for (ProductDto productDto : productDtos) {
            ProductViewModel productViewModel1 = new ProductViewModel();

            productViewModel1.setId(productDto.getProductId());
            productViewModel1.setName(productDto.getName());
            productViewModel1.setThumbnail(productDto.getThumbnail());
            productViewModel1.setPrice(productDto.getPrice());
            productViewModel1.setDescription(productDto.getDescription());
            productViewModel1.setCreatedDate(productDto.getCreatedDate());

            productViewModels.add(productViewModel1);
        }

        productDetailViewModel.setProductViewModels(productViewModels);

        productDetailViewModel.setProductViewModel(productViewModel);

        model.addAttribute("productDetailViewModel",productDetailViewModel);

//        return "client/single-product";
        return "product-details";
    }
}
