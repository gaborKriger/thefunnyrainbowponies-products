package com.codecool.kriger.productsservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {

    @GetMapping("/")
    public String index() {
        return "Hello page";
    }

}
