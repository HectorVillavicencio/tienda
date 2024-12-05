package com.tienda.tienda.persistence.crud;

import com.tienda.tienda.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByPrecioGreaterThan(float precio);
    List<Producto> findByPrecioLessThan(float precio);
    List<Producto> findByCategoriaContainingIgnoreCase(String categoria);
}

