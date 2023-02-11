package com.dotech.ecommerce.controller;


import com.dotech.ecommerce.model.ShoppingCart;
import com.dotech.ecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping_carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        super();
        this.shoppingCartService = shoppingCartService;
    }

    //CREATE READ UPDATE DELETE
    @GetMapping
    public List<ShoppingCart> getAllCartItem(){
        return shoppingCartService.getAllCartItem();
    }

    @PutMapping("/add/product={id}")
    public ResponseEntity<ShoppingCart> addToCart(@PathVariable("id") long productId){
        return new ResponseEntity<ShoppingCart>((MultiValueMap<String, String>) shoppingCartService.addToCart(productId), HttpStatus.OK);
    }


}
