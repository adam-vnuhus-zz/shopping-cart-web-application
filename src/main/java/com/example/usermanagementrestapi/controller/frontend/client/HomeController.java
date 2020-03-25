package com.example.usermanagementrestapi.controller.frontend.client;


import com.example.usermanagementrestapi.model.dto.CategoryDto;
import com.example.usermanagementrestapi.model.dto.ProductDto;
import com.example.usermanagementrestapi.model.request.view_model.CategoryViewModel;
import com.example.usermanagementrestapi.model.request.view_model.HomeViewModel;
import com.example.usermanagementrestapi.model.request.view_model.ProductViewModel;
import com.example.usermanagementrestapi.service.CategoryService;
import com.example.usermanagementrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/frontend/client")
//public class HomeController extends BaseController {
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String home(Model model,
                       @ModelAttribute("productname") ProductViewModel productName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal){

//        this.checkCookie(response,request,principal);

        HomeViewModel homeViewModel = new HomeViewModel();

        List<CategoryDto> categoryDtos =categoryService.getListCategory();
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtos) {
            CategoryViewModel categoryViewModel = new CategoryViewModel();
            categoryViewModel.setId(categoryDto.getCategoryId());
            categoryViewModel.setBrand(categoryDto.getName());
            categoryViewModel.setDescription(categoryDto.getDescription());
            categoryViewModel.setCreatedDate(categoryDto.getCreatedDate());
            categoryViewModels.add(categoryViewModel);
        }
        homeViewModel.setCategoryViewModels(categoryViewModels);

        List<ProductDto> productDtos = productService.getListProductNew();
        List<ProductViewModel> productViewModels = new ArrayList<>();

        for (ProductDto productDto : productDtos) {
            ProductViewModel productViewModel = new ProductViewModel();

            productViewModel.setId(productDto.getProductId());
            productViewModel.setName(productDto.getName());
            productViewModel.setThumbnail(productDto.getThumbnail());
            productViewModel.setPrice(productDto.getPrice());
            productViewModel.setDescription(productDto.getDescription());
            productViewModel.setCreatedDate(productDto.getCreatedDate());

            productViewModels.add(productViewModel);
        }

        homeViewModel.setProductViewModels(productViewModels);

        model.addAttribute("homeViewModel", homeViewModel);

//        return "client/index";
        return "index";
    }
}
