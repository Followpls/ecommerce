package com.dotech.ecommerce.service;

import com.dotech.ecommerce.model.Product;
import com.dotech.ecommerce.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {


    //Read
    List<ShoppingCart> getAllCartItem();
    ShoppingCart getCartItemById(long id);


    //Update
    Object addToCart(long productId);

    Object removeFromCart(long productId);

    //Delete
    void deleteCartItem(long id);


    //pagination & sorting
    List<ShoppingCart> getCartItemByPageSort(Integer pageNo, Integer pageSize, String sortBy);

}
