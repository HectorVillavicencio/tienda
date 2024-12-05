package com.tienda.tienda.domain.service;

import com.tienda.tienda.domain.Sale;
import com.tienda.tienda.domain.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAll(){
        return saleRepository.getAll();
    }

    public Optional<Sale> getSale(Integer idSale){
        return saleRepository.getSale(idSale);
    }

    public Sale save(Sale sale){
        return saleRepository.save(sale);
    }

    public boolean delete(Integer idSale){
        return getSale(idSale).map(sale -> {
            saleRepository.delete(idSale);
            return true;
        }).orElse(false);
    }

    public Optional<List<Sale>> getBySeller(Integer seller){
        return saleRepository.getBySeller(seller);
    }

    public Optional<List<Sale>> getByProduct(Integer product){
        return saleRepository.getByProduct(product);
    }


}
