package com.codecool.kriger.productsservice.service;

import com.codecool.kriger.productsservice.model.Product;
import com.codecool.kriger.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
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
        savedProduct.setLongDescription(product.getLongDescription());
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
        Example<Product> example = Example.of(product);
        return productRepository.exists(example);

//        List<Product> products = productRepository.findAll();
//        for (Product savedProduct : products) {
//            if (savedProduct.getName().equals(product.getName()) &
//                    savedProduct.getDescription().equals(product.getDescription()) &
//                    savedProduct.getLongDescription().equals(product.getLongDescription()) &
//                    savedProduct.getPrice().equals(product.getPrice()) &
//                    savedProduct.getImgPath().equals(product.getImgPath())) {
//                return true;
//            }
//        }
//        return false;
    }
}
