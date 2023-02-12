package com.dotech.ecommerce.service.impl;

import com.dotech.ecommerce.exception.ResourceNotFoundException;
import com.dotech.ecommerce.model.Product;
import com.dotech.ecommerce.repository.ProductRepository;
import com.dotech.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    //must-have object for PR to construct PS, Dependency Injection
    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
    }

    @Override
    public Product updateProduct(Product product, long id) {
        //check if product id is in DB
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        //update product
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setUnitPrice(product.getUnitPrice());

        //save the update
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public void deleteProduct(long id) {
        //check if product id is in DB
        productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id));

        //if exist then delete

        productRepository.deleteById(id);

    }

    @Override
    public List<Product> getProductByPageSort(Integer pageNo, Integer pageSize, String sortBy) {
        //API start page at 0, so page no have to -1
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

        Page<Product> pagingResult = productRepository.findAll(pageable);

        if (pagingResult.hasContent()){
            return pagingResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }


}
