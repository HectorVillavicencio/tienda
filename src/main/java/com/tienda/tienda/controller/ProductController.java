package com.tienda.tienda.controller;

import com.tienda.tienda.domain.Product;
import com.tienda.tienda.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")  // Ruta única para obtener todos los productos
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")  // Ruta única para obtener producto por id
    public Optional<Product> getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @PostMapping ("/save") // Ruta para guardar un nuevo producto
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return productService.delete(id);
    }

    @GetMapping("/name/{name}")  // Ruta para obtener productos por nombre
    public Optional<List<Product>> getByName(@PathVariable("name") String name) {
        return productService.getByName(name);
    }

    @GetMapping("/pricegt/{price}")  // Ruta para obtener productos por precio
    public Optional<List<Product>> getByPriceGreaterThan(@PathVariable("price") float price) {
        return productService.getByPriceGreaterThan(price);
    }

    @GetMapping("/pricelt/{price}")  // Ruta para obtener productos por precio
    public Optional<List<Product>> getByPriceLessThan(@PathVariable("price") float price) {
        return productService.getByPriceLessThan(price);
    }


    @GetMapping("/category/{category}")  // Ruta para obtener productos por categoría
    public Optional<List<Product>> getByCategory(@PathVariable("category") String category) {
        return productService.getByCategory(category);
    }
}