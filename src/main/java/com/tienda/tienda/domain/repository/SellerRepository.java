package com.tienda.tienda.domain.repository;

import com.tienda.tienda.domain.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerRepository {
    List<Seller> getAll();
    Optional<Seller> getSeller(int idSeller);
    Seller save(Seller seller);
    void delete(int idSeller);

}
