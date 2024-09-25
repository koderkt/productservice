package com.koderkt.productservice.exceptions;

import com.koderkt.productservice.models.Product;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String message){
        super(message);
    }

}
