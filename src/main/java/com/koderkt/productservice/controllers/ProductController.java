package com.koderkt.productservice.controllers;

import com.koderkt.productservice.models.Product;
import com.koderkt.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
        return new Product();
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id) {
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
    }
}
