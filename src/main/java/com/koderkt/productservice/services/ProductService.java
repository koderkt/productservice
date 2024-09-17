package com.koderkt.productservice.services;

import com.koderkt.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product addNewProduct(Product product);
    Product updateProduct(Product product);
    Product replaceProduct(Long id, Product product);
}
