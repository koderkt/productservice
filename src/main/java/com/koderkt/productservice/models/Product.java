package com.koderkt.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private Double price;
    private Category category;
    private String description;
    private String imageUrl;
}
