package com.dotech.ecommerce.controller;

import com.dotech.ecommerce.model.Product;
import com.dotech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    final private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    //Create Read Update Delete
    //build create product REST API
    //@RequestBody convert a json into java object
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
    }


    //build get all product REST API
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }


    //build get product by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }


    //build update product REST API
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,
                                                 @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }


    //build delete product REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {

        productService.deleteProduct(id);

        return new ResponseEntity<>("Product deleted successfully!", HttpStatus.OK);
    }


    //Pagination features
    //http://localhost:8080/api/products/page&sort?pageSize=2&pageNo=3&sortBy=unitPrice
    @GetMapping("page&sort")
    public ResponseEntity<List<Product>> getProductByPageSort(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Product> list = productService.getProductByPageSort(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }


}
