package com.tienda.tienda.domain.repository;

import com.tienda.tienda.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<Product> getProduct(int idProduct);
    Optional<List<Product>> getByName(String name);
    Optional<List<Product>> getByPriceGreaterThan(float price);
    Optional<List<Product>> getByPriceLessThan(float price);
    Optional<List<Product>> getByCategory(String category);
    Product save(Product product);
    void delete(int idProduct);
}
