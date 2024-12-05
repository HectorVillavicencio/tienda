package com.tienda.tienda.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int idProduct;
    private String name;
    private float price;
    private String category;

}
