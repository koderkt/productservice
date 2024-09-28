package com.koderkt.productservice.services;

import com.koderkt.productservice.models.Category;
import com.koderkt.productservice.models.Product;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
}
