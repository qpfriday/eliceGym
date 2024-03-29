package com.project.elicegym.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/{product_id}")
    public String getProduct(@PathVariable("product_id") Long product_id){
        return "product/product";
    }


    @GetMapping("/create")
    public String createProduct(){
        return "product/productForm";
    }

    @GetMapping("/list")
    public String productList(){
        return "product/productList";
    }
}
