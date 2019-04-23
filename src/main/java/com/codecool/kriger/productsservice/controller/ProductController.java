package com.codecool.kriger.productsservice.controller;

import com.codecool.kriger.productsservice.model.Product;
import com.codecool.kriger.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity index() {
        return new ResponseEntity(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
