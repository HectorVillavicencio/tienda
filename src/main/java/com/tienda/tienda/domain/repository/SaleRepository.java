package com.tienda.tienda.domain.repository;

import com.tienda.tienda.domain.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    List<Sale> getAll();
    Optional<Sale> getSale(int idSale);
    Optional<List<Sale>> getBySeller(int seller);
    Optional<List<Sale>> getByProduct(int product);
    Sale save(Sale sale);
    void delete(int idSale);

}
