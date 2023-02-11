package com.dotech.ecommerce.service;

import com.dotech.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    //Create/add/save
    Product saveProduct(Product product);

    //Read
    List<Product> getAllProduct();
    Product getProductById(long id);


    //Update
    Product updateProduct(Product product, long id);


    //Delete
    void deleteProduct(long id);
}
