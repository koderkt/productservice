package com.koderkt.productservice.services;

import com.koderkt.productservice.dtos.FakeStoreProductDto;
import com.koderkt.productservice.exceptions.ProductNotExistException;
import com.koderkt.productservice.models.Category;
import com.koderkt.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        Product p1 = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);
        if(p1 != null){
            return p1;
        }
        FakeStoreProductDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if (productDto == null) {
            throw new ProductNotExistException("Product with id " + id + " doesn't exist.");
        }
        Product p = convertFakeStoreProductToProduct(productDto);
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, p);
        return p;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] productsDto = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        assert productsDto != null;
        for (FakeStoreProductDto fakeStoreProduct : productsDto) {
            Product product = convertFakeStoreProductToProduct(fakeStoreProduct);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotExistException {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return List.of();
    }


    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor;
        responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Void deleteProduct(Long id) {

        return null;
    }

}
