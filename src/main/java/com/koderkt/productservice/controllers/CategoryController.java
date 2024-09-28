package com.koderkt.productservice.controllers;

import com.koderkt.productservice.models.Category;
import com.koderkt.productservice.models.Product;
import com.koderkt.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping("")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
