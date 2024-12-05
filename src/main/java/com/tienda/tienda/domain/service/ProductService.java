package com.tienda.tienda.domain.service;

import com.tienda.tienda.domain.Product;
import com.tienda.tienda.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        try {
            List<Product> products = productRepository.getAll();
            if (products.isEmpty()) {
                System.out.println("No se encontraron productos.");
            }
            return products;
        } catch (DataAccessException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al obtener todos los productos. Intente más tarde.", e) {};
        }
    }

    public Optional<Product> getProduct(Integer idProduct) {
        try {
            Optional<Product> product = productRepository.getProduct(idProduct);
            if (!product.isPresent()) {
                System.out.println("No se encontró el producto con el código proporcionado");
            }
            return product;
        } catch (EntityNotFoundException e) {
            System.out.println("Error al buscar el producto con ID " + idProduct + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new EntityNotFoundException("Producto no encontrado. Intente más tarde.");
        } catch (Exception e) {
            System.out.println("Error al buscar el producto con ID " + idProduct + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar el producto. Intente más tarde.", e);
        }
    }

    public Product save(Product product) {
        try {
            return productRepository.save(product);
        } catch (DataAccessException e) {
            System.out.println("Error al guardar el producto: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al guardar el producto. Intente más tarde.", e) {};
        }
    }

    public boolean delete(Integer idProduct) {
        try {
            Optional<Product> product = getProduct(idProduct);
            if (product.isPresent()) {
                productRepository.delete(idProduct);
                return true;
            } else {
                System.out.println("No se encontró el producto con ID " + idProduct);
                return false;
            }
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar el producto con ID " + idProduct + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al eliminar el producto. Intente más tarde.", e) {};
        }
    }

    public Optional<List<Product>> getByName(String nombre) {
        try {
            Optional<List<Product>> products = productRepository.getByName(nombre);
            if (products.isEmpty()) {
                System.out.println("No se encontraron productos con el nombre: " + nombre);
            }
            return products;
        } catch (DataAccessException e) {
            System.out.println("Error al buscar los productos por nombre: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al buscar los productos por nombre. Intente más tarde.", e) {};
        }
    }

    public Optional<List<Product>> getByPriceGreaterThan(float price) {
        try {
            Optional<List<Product>> products = productRepository.getByPriceGreaterThan(price);
            if (products.isEmpty()) {
                System.out.println("No se encontraron productos con un precio mayor a " + price);
            }
            return products;
        } catch (DataAccessException e) {
            System.out.println("Error al buscar los productos con precio mayor a " + price + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al buscar los productos por precio. Intente más tarde.", e) {};
        }
    }

    public Optional<List<Product>> getByPriceLessThan(float price) {
        try {
            Optional<List<Product>> products = productRepository.getByPriceLessThan(price);
            if (products.isEmpty()) {
                System.out.println("No se encontraron productos con un precio menor a " + price);
            }
            return products;
        } catch (DataAccessException e) {
            System.out.println("Error al buscar los productos con precio menor a " + price + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al buscar los productos por precio. Intente más tarde.", e) {};
        }
    }

    public Optional<List<Product>> getByCategory(String category) {
        try {
            Optional<List<Product>> products = productRepository.getByCategory(category);
            if (products.isEmpty()) {
                System.out.println("No se encontraron productos con la categoría: " + category);
            }
            return products;
        } catch (DataAccessException e) {
            System.out.println("Error al buscar los productos por categoría: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al buscar los productos por categoría. Intente más tarde.", e) {};
        }
    }
}
