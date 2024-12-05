package com.tienda.tienda.persistence.crud;

import com.tienda.tienda.persistence.entity.Vendedor;
import org.springframework.data.repository.CrudRepository;

public interface VendedorCrudRepository extends CrudRepository<Vendedor, Integer> {
}
