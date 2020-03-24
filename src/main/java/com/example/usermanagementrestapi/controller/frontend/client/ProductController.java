package com.example.usermanagementrestapi.controller.frontend.client;

import com.example.usermanagementrestapi.entity.Category;
import com.example.usermanagementrestapi.entity.Product;
import com.example.usermanagementrestapi.model.request.view_model.HomeViewModel;
import com.example.usermanagementrestapi.model.request.view_model.ProductViewModel;
import com.example.usermanagementrestapi.service.CategoryService;
import com.example.usermanagementrestapi.service.ProductService;
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
import java.util.ArrayList;
import java.util.List;

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
                          @RequestParam(name = "size", required = false, defaultValue = "4") Integer size,
                          @RequestParam(name = "sortByPrice", required = false) String sort
                          ) {

        HomeViewModel homeViewModel = new HomeViewModel();

        Sort sortable =Sort.by(Sort.Direction.ASC,"id");
        if(sort != null) {
            if (sort.equals("ASC")) {
                sortable =  Sort.by(Sort.Direction.ASC,"price");
            }else {
                sortable = Sort.by(Sort.Direction.DESC,"price");
            }
        }

        Pageable pageable = PageRequest.of(page,size,sortable);

        Page<Product> productPage = null;

        if (categoryId != null) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, categoryId, null);
            Category category = categoryService.getOne(categoryId);
            //Get name of category
            homeViewModel.setKeyWord(category.getBrand());
        } else if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, productName.getName().trim());
            homeViewModel.setKeyWord("Kết quả tìm kiếm cho: " + productName.getName());
        } else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, null);
        }
        List<ProductViewModel> productViewModels = new ArrayList<>();

        for (Product product : productPage.getContent()) {
            ProductViewModel productViewModel = new ProductViewModel();
            if (product.getCategory() == null) {
                productViewModel.setCategoryBrand("Unknown");
            } else {
                productViewModel.setCategoryBrand(product.getCategory().getBrand());
            }
            productViewModel.setId(product.getProductId());
            productViewModel.setName(product.getName());
            productViewModel.setThumbnail(product.getThumbnail());
            productViewModel.setPrice(product.getPrice());
            productViewModel.setDescription(product.getDescription());
            productViewModel.setCreatedDate(product.getCreateDate());

            productViewModels.add(productViewModel);
        }

        homeViewModel.setProductViewModels(productViewModels);
        if (productViewModels.size() == 0) {
            homeViewModel.setKeyWord("Không tìm thấy");
        }


        model.addAttribute("homeViewModel", homeViewModel);
        model.addAttribute("page", productPage);
//        return "client/listProduct";
        return "shop";
    }
}
