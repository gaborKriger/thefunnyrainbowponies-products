package com.codecool.kriger.productsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double prize;
    private String imgPath;

}
