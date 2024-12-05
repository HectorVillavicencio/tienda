package com.tienda.tienda.persistence.crud;

import com.tienda.tienda.persistence.entity.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VentaCrudRepository extends CrudRepository<Venta, Integer> {

    List<Venta> findByIdVendedor(int idVendedor);
    List<Venta> findByIdProducto(int idProducto);




}

