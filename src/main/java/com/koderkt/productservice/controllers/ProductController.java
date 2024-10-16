package com.koderkt.productservice.controllers;

import com.koderkt.productservice.commons.AuthenticationCommons;
import com.koderkt.productservice.dtos.Role;
import com.koderkt.productservice.dtos.UserDto;
import com.koderkt.productservice.exceptions.ProductNotExistException;
import com.koderkt.productservice.models.Product;
import com.koderkt.productservice.services.ProductService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
//        UserDto userDto = authenticationCommons.validateToken(token);
//        if (userDto == null) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        boolean isAdmin = false;
//
//        for (Role role : userDto.getRoles()) {
//            if (role.getName() == "ADMIN") {
//                isAdmin = true;
//                break;
//            }
//        }
//        if (!isAdmin) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        return new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistException {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotExistException {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotExistException {
        return productService.replaceProduct(id, product);
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getAllProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotExistException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.NO_CONTENT);
    }
}
