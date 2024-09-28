package com.koderkt.productservice.services;

import com.koderkt.productservice.exceptions.ProductNotExistException;
import com.koderkt.productservice.models.Category;
import com.koderkt.productservice.models.Product;
import com.koderkt.productservice.repositories.CategoryRepository;
import com.koderkt.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotExistException("Product with id: " + id + " doesn't exists.");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product addNewProduct(Product product) {

        Category category = product.getCategory();
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
        if (optionalCategory.isEmpty()) {
            product.setCategory(categoryRepository.save(category));
        } else {
            product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) throw new ProductNotExistException("Product with id: " + id + " doesn't exist");

        Product savedProduct = productOptional.get();
        if (product.getTitle() != null) {
            savedProduct.setTitle(product.getTitle());
        }
        if (product.getDescription() != null) {
            savedProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            savedProduct.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            savedProduct.setImageUrl(product.getImageUrl());
        }
        if (product.getCategory() != null) {
            savedProduct.setCategory(product.getCategory());
        }
        return productRepository.save(savedProduct);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotExistException("Product with id: " + id + " doesn't exist");
        }
        Product savedProduct = productOptional.get();
        savedProduct.setTitle(product.getTitle());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setCategory(product.getCategory());

        return productRepository.save(savedProduct);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {

        return productRepository.findByCategoryName(categoryName);
    }
    @Override
    public Void deleteProduct(Long id) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotExistException("Product with id: " + id + " doesn't exist");
        }
        productRepository.deleteById(id);
        return null;
    }
}
