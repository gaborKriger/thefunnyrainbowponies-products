package com.codecool.kriger.productsservice.controller;

import com.codecool.kriger.productsservice.model.Product;
import com.codecool.kriger.productsservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<String> root() {
        log.info("Opening root... ;)");
        String funnyMessage = "This is the funny rainbow ponies site! \n Please leave this site, until it's to late...";
        return new ResponseEntity<>(funnyMessage, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        log.info("Fetching all products...");
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            log.warn("No product available!");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        log.info("Sorting list...");
        products.sort(Comparator.comparingLong(Product::getId));
        log.info("Sorted list.");
        log.info("Fetched all products.");
        return new ResponseEntity<>(products,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        log.info("Creating a new product...");
        boolean isSaved = productService.saveProduct(product);
        if (!isSaved) {
            log.warn("Product already exists!");
            return new ResponseEntity(new HttpHeaders(), HttpStatus.CONFLICT);
        }
        log.info("Created product. Product: " + product.toString());
        return new ResponseEntity(new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/products")
    public ResponseEntity deleteProducts() {
        log.info("Deleting all products...");
        boolean isDeleted = productService.deleteProducts();
        if (!isDeleted) {
            log.warn("No product available!");
            return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        log.info("Deleted all products.");
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        log.info("Fetching one product... ID: " + id);
        Product product = productService.getProduct(id);
        if (product == null) {
            log.warn("No product available!");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        log.info("Fetched one product.");
        return new ResponseEntity<>(product, new HttpHeaders(),  HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity modifyProduct(@PathVariable Long id, @RequestBody Product product) {
        log.info("Modifying one product... ID: " + id);
        boolean isModify = productService.modifyProduct(id, product);
        if (!isModify) {
            log.warn("No product available!");
            return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        log.warn("Modified one product.");
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        log.info("Deleting one product... ID: " + id);
        boolean isDeleted = productService.deleteProduct(id);
        if (!isDeleted) {
            log.warn("No product available!");
            return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
        }
        log.info("Deleted one product.");
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }
}
