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
        // TODO save if not exists
        List<Product> products = productRepository.findAll();

        for (Product savedProduct : products) {
            if (savedProduct.getName().equals(product.getName()) &
                savedProduct.getDescription().equals(product.getName()) &
                savedProduct.getPrice().equals(product.getPrice()) &
                savedProduct.getImgPath().equals(product.getImgPath())) {
                    return false;
            }
        }

        productRepository.save(product);
        return true;
    }

}
