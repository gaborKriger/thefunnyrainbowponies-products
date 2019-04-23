package com.codecool.kriger.productsservice.repository;

import com.codecool.kriger.productsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
