package com.dotech.ecommerce.service;

import com.dotech.ecommerce.model.Product;
import com.dotech.ecommerce.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    Product checkProductById(long productId);

    //Read
    List<ShoppingCart> getAllCartItem();
    ShoppingCart getCartItemById(long id);


    //Update
    ShoppingCart addToCart(long productId);

    ShoppingCart removeFromCart(long productId);

    //Delete
    void deleteCartItem(long id);


    //pagination & sorting
    List<ShoppingCart> getCartItemByPageSort(Integer pageNo, Integer pageSize, String sortBy);

}
