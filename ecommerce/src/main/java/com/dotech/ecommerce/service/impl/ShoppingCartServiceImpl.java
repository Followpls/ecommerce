package com.dotech.ecommerce.service.impl;

import com.dotech.ecommerce.exception.ResourceNotFoundException;
import com.dotech.ecommerce.model.Product;
import com.dotech.ecommerce.model.ShoppingCart;
import com.dotech.ecommerce.repository.ProductRepository;
import com.dotech.ecommerce.repository.ShoppingCartRepository;
import com.dotech.ecommerce.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ProductRepository productRepository;
    private ShoppingCartRepository shoppingCartRepository;


    public ShoppingCartServiceImpl(ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository) {
        super();
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    //R
    @Override
    public List<ShoppingCart> getAllCartItem() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart getCartItemById(long cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart with Cart id: " + cartId));
    }

    //U
    @Override
    public Object addToCart(long productId) {

        //check if product is exists in product repository, if no, throw resource not found exception
        if (productRepository.findById(productId).isEmpty()) {
            return new ResourceNotFoundException("Product not exist with id: " + productId);
        }

        //get the product object from product repository
        Product product = productRepository.findById(productId).get();

        //for each cart item in shopping cart

        //if found cart item name and product name is equals, add quantity by 1
        List<ShoppingCart> cartItems = shoppingCartRepository.findAll();
        for (ShoppingCart item : cartItems) {
            if (product.getName().equals(item.getProductName())) {
                item.setProductQuantity(item.getProductQuantity() + 1);
                item.setTotalAmount(item.getProductQuantity() * item.getUnitPrice());
                shoppingCartRepository.save(item);
                return item;
            }
        }


        ShoppingCart newCartItem = new ShoppingCart();
        shoppingCartRepository.save(newCartItem);
        newCartItem.setProductName(product.getName());
        newCartItem.setProductQuantity(1);
        newCartItem.setUnitPrice(product.getUnitPrice());
        newCartItem.setTotalAmount(newCartItem.getProductQuantity() * newCartItem.getUnitPrice());
        shoppingCartRepository.save(newCartItem);
        return newCartItem;
    }

    @Override
    public Object removeFromCart(long productId) {
        if (productRepository.findById(productId).isEmpty()) {
            return new ResourceNotFoundException("Product not exist with id: " + productId);
        }

        //get the product object from product repository
        Product product = productRepository.findById(productId).get();

        //for each cart item in shopping cart
        List<ShoppingCart> cartItems = shoppingCartRepository.findAll();
        for (ShoppingCart item : cartItems) {
            if (product.getName().equals(item.getProductName()) && item.getProductQuantity()>0) {
                item.setProductQuantity(item.getProductQuantity() - 1);
                item.setTotalAmount(item.getProductQuantity() * item.getUnitPrice());
                shoppingCartRepository.save(item);
                return item;
            }
        }
        return cartItems;
    }


    //D
    @Override
    public void deleteCartItem(long cartId) {
        shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart with Cart id: " + cartId));

        shoppingCartRepository.deleteById(cartId);
    }
}
