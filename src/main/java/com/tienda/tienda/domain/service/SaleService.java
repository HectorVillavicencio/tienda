package com.tienda.tienda.domain.service;

import com.tienda.tienda.domain.Sale;
import com.tienda.tienda.domain.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAll() {
        try {
            List<Sale> sales = saleRepository.getAll();
            if (sales.isEmpty()) {
                System.out.println("No se encontraron ventas.");
            }
            return sales;
        } catch (DataAccessException e) {
            System.out.println("Error al obtener todas las ventas: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al obtener todas las ventas. Intente más tarde.", e) {};
        }
    }

    public Optional<Sale> getSale(Integer idSale) {
        try {
            Optional<Sale> sale = saleRepository.getSale(idSale);
            if (!sale.isPresent()) {
                System.out.println("No se encontró la venta con el ID proporcionado: " + idSale);
            }
            return sale;
        } catch (EntityNotFoundException e) {
            System.out.println("Error al buscar la venta con ID " + idSale + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new EntityNotFoundException("Venta no encontrada. Intente más tarde.");
        } catch (Exception e) {
            System.out.println("Error al buscar la venta con ID " + idSale + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar la venta. Intente más tarde.", e);
        }
    }

    public Sale save(Sale sale) {
        try {
            return saleRepository.save(sale);
        } catch (DataAccessException e) {
            System.out.println("Error al guardar la venta: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al guardar la venta. Intente más tarde.", e) {};
        }
    }

    public boolean delete(Integer idSale) {
        try {
            return getSale(idSale).map(sale -> {
                saleRepository.delete(idSale);
                return true;
            }).orElseGet(() -> {
                System.out.println("No se encontró la venta con ID " + idSale);
                return false;
            });
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar la venta con ID " + idSale + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al eliminar la venta. Intente más tarde.", e) {};
        }
    }

    public Optional<List<Sale>> getBySeller(Integer seller) {
        try {
            Optional<List<Sale>> sales = saleRepository.getBySeller(seller);
            if (sales.isEmpty()) {
                System.out.println("No se encontraron ventas realizadas por el vendedor con ID: " + seller);
            }
            return sales;
        } catch (DataAccessException e) {
            System.out.println("Error al buscar ventas por vendedor: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al buscar ventas por vendedor. Intente más tarde.", e) {};
        }
    }

    public Optional<List<Sale>> getByProduct(Integer product) {
        try {
            Optional<List<Sale>> sales = saleRepository.getByProduct(product);
            if (sales.isEmpty()) {
                System.out.println("No se encontraron ventas del producto con ID: " + product);
            }
            return sales;
        } catch (DataAccessException e) {
            System.out.println("Error al buscar ventas por producto: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al buscar ventas por producto. Intente más tarde.", e) {};
        }
    }
}