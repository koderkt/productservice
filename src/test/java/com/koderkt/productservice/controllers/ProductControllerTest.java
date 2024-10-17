package com.koderkt.productservice.controllers;

import com.koderkt.productservice.exceptions.ProductNotExistException;
import com.koderkt.productservice.models.Product;
import com.koderkt.productservice.repositories.ProductRepository;
import com.koderkt.productservice.services.ProductService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    @Qualifier("selfProductService")
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testProductsSameAsService() {
        List<Product> products = new ArrayList<Product>();
        Product product1 = new Product();
        product1.setTitle("iPhone 15");
        product1.setPrice(1200000.0);
        products.add(product1);
        products.add(product1);

        Product product2 = new Product();
        product2.setTitle("iPhone 14");
        product2.setPrice(800000.0);
        products.add(product2);

        Product product3 = new Product();
        product3.setTitle("iPhone 16");
        product3.setPrice(1400000.0);
        products.add(product3);

        List<Product> productsToPass = new ArrayList<>();
        for (Product product : products) {
            Product pro = new Product();
            pro.setTitle(product.getTitle());
            pro.setPrice(product.getPrice());
            productsToPass.add(pro);
        }
        when(
                productService.getAllProducts()
        ).
                thenReturn(
                        productsToPass
                );

        ResponseEntity<List<Product>> response = productController.getAllProducts();
        List<Product> productsInResponse = response.getBody();

        assert productsInResponse != null;
        assertEquals(products.size(), productsInResponse.size());

        for (int i = 0; i < productsInResponse.size(); i++) {
            assertEquals(
                    products.get(i).getTitle(),
                    productsInResponse.get(i).getTitle())
            ;
        }
    }

//    @Test
//    void getNotExistingProductThrowsException() {
//         when(
//                productRepository.findById(10L)
//        ).thenReturn(
//                Optional.empty()
//        );
//
//        assertThrows(ProductNotExistException.class,() -> productController.getSingleProduct(10L));
//    }
}