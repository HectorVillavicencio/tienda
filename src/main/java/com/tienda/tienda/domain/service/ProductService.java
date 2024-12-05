package com.tienda.tienda.domain.service;

import com.tienda.tienda.domain.Product;
import com.tienda.tienda.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    };

    public Optional<Product> getProduct(Integer idProduct){
        return productRepository.getProduct(idProduct);
    };

    public Product save(Product product){
        return productRepository.save(product);
    };

    public boolean delete(Integer idProduct){
        return getProduct(idProduct).map(product -> {
            productRepository.delete(idProduct);
            return true;
        }).orElse(false);
    };

    public Optional<List<Product>> getByName(String nombre){
        return productRepository.getByName(nombre);
    }

    public Optional<List<Product>> getByPriceGreaterThan(float price){
        return productRepository.getByPriceGreaterThan(price);
    }

    public Optional<List<Product>> getByPriceLessThan(float price){
        return productRepository.getByPriceLessThan(price);
    }

    public Optional<List<Product>> getByCategory(String category){
        return productRepository.getByCategory(category);
    }


}
