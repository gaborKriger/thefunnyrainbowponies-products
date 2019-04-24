package com.codecool.kriger.productsservice.service;

import com.codecool.kriger.productsservice.model.Product;
import com.codecool.kriger.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public boolean saveProduct(Product product) {
        if (isExists(product)) {
            return false;
        }
        productRepository.save(product);
        return true;
    }

    public boolean deleteProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return false;
        }
        productRepository.deleteAll();
        return true;
    }

    public boolean deleteProduct(Product product) {
        if (!isExists(product)) {
            return false;
        }
        productRepository.delete(product);
        return false;
    }


    private boolean isExists(Product product) {
        List<Product> products = productRepository.findAll();
        for (Product savedProduct : products) {
            if (savedProduct.getName().equals(product.getName()) &
                    savedProduct.getDescription().equals(product.getDescription()) &
                    savedProduct.getPrice().equals(product.getPrice()) &
                    savedProduct.getImgPath().equals(product.getImgPath())) {
                return true;
            }
        }
        return false;
    }
}
