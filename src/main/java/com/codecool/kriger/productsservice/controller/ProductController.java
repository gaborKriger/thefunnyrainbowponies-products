package com.codecool.kriger.productsservice.controller;

import com.codecool.kriger.productsservice.model.Product;
import com.codecool.kriger.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity root() {
        // TODO funny
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity getProducts() {
        // TODO error handling (if products empty...etc)
        return new ResponseEntity(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        // TODO error handling
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/products")
    public ResponseEntity deleteProducts() {
        // TODO business logic
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        // TODO business logic
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity modifyProduct(@PathVariable Long id, @RequestBody Product product) {
        // TODO business logic
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/products/{id")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        // TODO error handling
        return new ResponseEntity(HttpStatus.OK);
    }
}
