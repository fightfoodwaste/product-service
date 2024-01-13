package com.fightfoodwaste.products_service.exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(){
        super("Product not found!");
    }
}
