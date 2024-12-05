package com.tienda.tienda.persistence;

import com.tienda.tienda.domain.Product;
import com.tienda.tienda.domain.repository.ProductRepository;
import com.tienda.tienda.persistence.crud.ProductoCrudRepository;
import com.tienda.tienda.persistence.entity.Producto;
import com.tienda.tienda.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        try {
            List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
            return mapper.toProducts(productos);
        } catch (Exception e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al obtener todos los productos. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<Product> getProduct(int productID) {
        try {
            Optional<Product> product = productoCrudRepository.findById(productID).map(producto -> mapper.toProduct(producto));

            if (!product.isPresent()) {
                System.out.println("No se encontró el producto con el código proporcionado");
            }

            return product;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar el producto. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<List<Product>> getByName(String nombre) {

        List<Producto> productos = productoCrudRepository.findByNombreContainingIgnoreCase(nombre);
        try {
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos por nombre");
            }
            return Optional.of(mapper.toProducts(productos));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar los productos por nombre. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<List<Product>> getByPriceGreaterThan(float price) {
        List<Producto> productos = productoCrudRepository.findByPrecioGreaterThan(price);
        try {
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos por precio");
            }
            return Optional.of(mapper.toProducts(productos));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar los productos por precio. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<List<Product>> getByPriceLessThan(float price) {
        List<Producto> productos = productoCrudRepository.findByPrecioLessThan(price);
        try {
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos por precio");
            }
            return Optional.of(mapper.toProducts(productos));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar los productos por precio. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<List<Product>> getByCategory(String category) {
        List<Producto> productos = productoCrudRepository.findByCategoriaContainingIgnoreCase(category);
        try {
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos por categoria");
            }
            return Optional.of(mapper.toProducts(productos));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar los productos por categoria. Intente más tarde.", e);
        }
    }

    @Override
    public Product save(Product product) {
        try {
            Producto producto = mapper.toProducto(product);
            return mapper.toProduct(productoCrudRepository.save(producto));
        } catch (Exception e) {
            System.out.println("Error al guardar al producto: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al guardar al producto. Intente más tarde.", e);
        }
    }

    @Override
    public void delete(int codigo) {
        try {
            productoCrudRepository.deleteById(codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar al producto con el código proporcionado: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al eliminar al producto. Intente más tarde.", e);
        }
    }
}