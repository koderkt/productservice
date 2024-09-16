package com.koderkt.productservice.services;

import com.koderkt.productservice.models.Product;

public interface ProductService {
    Product getSingleProduct(Long id);
}
