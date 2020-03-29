package com.example.shoppingcart.controller.frontend.client;

import com.example.shoppingcart.entity.Category;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.model.request.view_model.HomeViewModel;
import com.example.shoppingcart.model.request.view_model.ProductViewModel;
import com.example.shoppingcart.service.CategoryService;
import com.example.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String product(Model model,
                          @Valid @ModelAttribute("productname") ProductViewModel productName,
                          @RequestParam(name = "categoryId", required = false) Integer categoryId,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "3") Integer size,
                          @RequestParam(name = "sortByPrice", required = false) String sort
    ) {

        HomeViewModel homeViewModel = new HomeViewModel();

        Sort sortable = Sort.by(Sort.Direction.ASC, "id");
        if (sort != null) {
            if (sort.equals("ASC")) {
                sortable = Sort.by(Sort.Direction.ASC, "price");
            } else {
                sortable = Sort.by(Sort.Direction.DESC, "price");
            }
        }

        Pageable pageable = PageRequest.of(page, size, sortable);

        Page<Product> productPage = null;

        if (categoryId != null) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, categoryId, null);
            Category category = categoryService.getOne(categoryId);
            //Get name of category
            homeViewModel.setKeyWord(category.getBrand());
        } else if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, productName.getName().trim());
            homeViewModel.setKeyWord("Result for: " + productName.getName());
        } else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, null);
        }


        homeViewModel.setProductViewModels(productService.getListProductViewModel(productPage));
        if (productService.getListProductViewModel(productPage).size() == 0) {
            homeViewModel.setKeyWord("Not Found");
        }


        model.addAttribute("homeViewModel", homeViewModel);
        model.addAttribute("page", productPage);

        return "shop";
    }
}
