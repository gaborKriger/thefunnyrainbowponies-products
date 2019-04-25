package com.codecool.kriger.productsservice.controller;

import com.codecool.kriger.productsservice.model.Product;
import com.codecool.kriger.productsservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<String> root() {
        String funnyMessage = "This is the funny rainbow ponies site! \n Please leave this site, until it's to late...";
        return new ResponseEntity<>(funnyMessage, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        boolean isSaved = productService.saveProduct(product);
        if (!isSaved) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/products")
    public ResponseEntity deleteProducts() {
        boolean isDeleted = productService.deleteProducts();
        if (!isDeleted) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, new HttpHeaders(),  HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity modifyProduct(@PathVariable Long id, @RequestBody Product product) {
        boolean isModify = productService.modifyProduct(id, product);
        if (!isModify) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (!isDeleted) {
            return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }
}
