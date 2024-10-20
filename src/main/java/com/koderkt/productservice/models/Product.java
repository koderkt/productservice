package com.koderkt.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    private String title;
    private Double price;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private String description;
    private String imageUrl;
}
