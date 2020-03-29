package com.example.shoppingcart.controller.frontend.client;


import com.example.shoppingcart.model.request.view_model.ProductDetailViewModel;
import com.example.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Integer productId,
                                Model model) {

        ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel();

        productDetailViewModel.setProductViewModels(productService.getListProductViewModel());
        productDetailViewModel.setProductViewModel(productService.getProductViewModelByProductId(productId));

        model.addAttribute("productDetailViewModel", productDetailViewModel);

        return "product-details";
    }
}
