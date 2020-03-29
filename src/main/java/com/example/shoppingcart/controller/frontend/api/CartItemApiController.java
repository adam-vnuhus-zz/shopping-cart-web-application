package com.example.shoppingcart.controller.frontend.api;

import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.entity.ShoppingCart;
import com.example.shoppingcart.model.api.BaseApiResult;
import com.example.shoppingcart.model.dto.CartItemDto;
import com.example.shoppingcart.service.CartItemService;
import com.example.shoppingcart.service.ProductService;
import com.example.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartItemApiController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;


    @Autowired
    private ShoppingCartService shoppingCartService;


    @DeleteMapping("/carts/{id}")
    public BaseApiResult deleteCartProduct(@PathVariable int id) {

        BaseApiResult result = new BaseApiResult();

        try {
            if (cartItemService.deleteCartItem(id)) {
                result.setMessage("Delete success");
                result.setSuccess(true);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setSuccess(false);
        result.setMessage("Fail!");
        return result;
    }


    @PutMapping("/carts")
    public BaseApiResult updateCartProduct(@RequestBody CartItemDto cartItemDto) {

        BaseApiResult result = new BaseApiResult();

        try {
            if (cartItemDto.getId() > 0 && cartItemDto.getAmount() > 0) {
                CartItem cartItemEntity = cartItemService.findOne(cartItemDto.getId());

                if (cartItemEntity != null) {
                    cartItemEntity.setAmount(cartItemDto.getAmount());
                    cartItemService.updateCartItem(cartItemEntity);
                    result.setMessage("Update Cart Product success !");
                    result.setSuccess(true);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setMessage("Fail!");
        result.setSuccess(false);
        return result;
    }

    @PostMapping("/carts")
    public BaseApiResult addCart(@RequestBody CartItemDto cartItemDto) {

        BaseApiResult result = new BaseApiResult();
        try {
            if (cartItemDto.getAmount() > 0 & cartItemDto.getProductId() > 0) {
                ShoppingCart shoppingCart = shoppingCartService.findByUserName(cartItemDto.getUsername());
                Product product = productService.getOne(cartItemDto.getProductId());
                if (shoppingCart != null & product != null) {
                    CartItem cartItem = cartItemService.findFirstCartItemByShoppingCartIdAndProductId(shoppingCart.getShoppingCartId(), product.getProductId());
                    if (cartItem != null) {
                        cartItem.setAmount(cartItem.getAmount() + cartItemDto.getAmount());
                        cartItemService.updateCartItem(cartItem);
                    } else {
                        CartItem cartItem1 = new CartItem();
                        cartItem1.setAmount(cartItemDto.getAmount());
                        cartItem1.setShoppingCart(shoppingCart);
                        cartItem1.setProduct(product);
                        cartItemService.createCartItem(cartItem1);
                    }
                    result.setMessage("Add to cart successfully!");
                    result.setSuccess(true);

                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setMessage("You need to login!");

        result.setSuccess(false);

        return result;
    }

}
