package com.dotech.ecommerce.service.impl;

import com.dotech.ecommerce.exception.ResourceNotFoundException;
import com.dotech.ecommerce.model.Product;
import com.dotech.ecommerce.model.ShoppingCart;
import com.dotech.ecommerce.repository.ProductRepository;
import com.dotech.ecommerce.repository.ShoppingCartRepository;
import com.dotech.ecommerce.service.ShoppingCartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    @Override
    public Product checkProductById(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + productId));
    }


    //U
    @Override
    public ShoppingCart addToCart(long productId) {

        //get the product object from product repository
        Product product = checkProductById(productId);

        //for each cart item in shopping cart
        //if found cart item name and product name is equals, add quantity by 1
        List<ShoppingCart> cartItems = shoppingCartRepository.findAll();
        for (ShoppingCart cartItem : cartItems) {
            if (product.getName().equals(cartItem.getProductName())) {
                cartItem.setProductQuantity(cartItem.getProductQuantity() + 1);
                cartItem.setTotalAmount(cartItem.getProductQuantity() * cartItem.getUnitPrice());
                shoppingCartRepository.save(cartItem);
                return cartItem;
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
    public ShoppingCart removeFromCart(long productId) {


        //get the product object from product repository
        Product product = checkProductById(productId);

        //for each cart item in shopping cart
        List<ShoppingCart> cartItems = shoppingCartRepository.findAll();
        for (ShoppingCart cartItem : cartItems) {
            if (product.getName().equals(cartItem.getProductName()) && cartItem.getProductQuantity() > 1) {
                cartItem.setProductQuantity(cartItem.getProductQuantity() - 1);
                cartItem.setTotalAmount(cartItem.getProductQuantity() * cartItem.getUnitPrice());
                shoppingCartRepository.save(cartItem);
                return cartItem;
            } else if (product.getName().equals(cartItem.getProductName()) && cartItem.getProductQuantity() == 1) {
                deleteCartItem(cartItem.getId());
            }
        }
        return null;
    }

    //D
    @Override
    public void deleteCartItem(long cartId) {
        shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart with Cart id: " + cartId));

        shoppingCartRepository.deleteById(cartId);
    }

    @Override
    public List<ShoppingCart> getCartItemByPageSort(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

        Page<ShoppingCart> pagingResult = shoppingCartRepository.findAll(pageable);

        if (pagingResult.hasContent()) {
            return pagingResult.getContent();
        } else {
            return new ArrayList<ShoppingCart>();
        }
    }
}


