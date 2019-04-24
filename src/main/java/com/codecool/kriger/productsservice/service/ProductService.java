package com.codecool.kriger.productsservice.service;

import com.codecool.kriger.productsservice.model.Product;
import com.codecool.kriger.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public boolean modifyProduct(Long id, Product product) {
        Optional<Product> savedProduct = productRepository.findById(id);
        if (savedProduct.isPresent()) {
            updateFieldsAndSave(product, savedProduct.get());
            return true;
        }
        return false;
    }

    private void updateFieldsAndSave(Product product, Product savedProduct) {
        savedProduct.setName(product.getName());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setImgPath(product.getImgPath());
        productRepository.save(savedProduct);
    }

    public boolean deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            return true;
        }
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
