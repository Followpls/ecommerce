package com.dotech.ecommerce.controller;


import com.dotech.ecommerce.model.Product;
import com.dotech.ecommerce.model.ShoppingCart;
import com.dotech.ecommerce.repository.ShoppingCartRepository;
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
    final private ShoppingCartService shoppingCartService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

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
        return new ResponseEntity<ShoppingCart>(shoppingCartService.addToCart(productId), HttpStatus.OK);
    }

    @PutMapping("/remove/product={id}")
    public ResponseEntity<ShoppingCart> removeFromCart(@PathVariable("id") long productId){
        return new ResponseEntity<ShoppingCart>(shoppingCartService.removeFromCart(productId), HttpStatus.OK);
    }

    //Pagination features
    //http://localhost:8080/api/shopping_carts/page&sort?pageSize=3&pageNo=1&sortBy=productName
    @GetMapping("/page&sort")
    public ResponseEntity<List<ShoppingCart>> getCartItemByPageSort(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<ShoppingCart> list = shoppingCartService.getCartItemByPageSort(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<ShoppingCart>>(list, HttpStatus.OK);
    }


}
