package com.tienda.tienda.domain.service;

import com.tienda.tienda.domain.Product;
import com.tienda.tienda.domain.Sale;
import com.tienda.tienda.domain.Seller;
import com.tienda.tienda.domain.repository.ProductRepository;
import com.tienda.tienda.domain.repository.SaleRepository;
import com.tienda.tienda.domain.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Seller> getAll() {
        try {
            List<Seller> sellers = sellerRepository.getAll();
            if (sellers.isEmpty()) {
                System.out.println("No se encontraron vendedores.");
            }
            return sellers;
        } catch (DataAccessException e) {
            System.out.println("Error al obtener todos los vendedores: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al obtener todos los vendedores. Intente más tarde.", e) {};
        }
    }

    public Optional<Seller> getSeller(Integer idSeller) {
        try {
            Optional<Seller> seller = sellerRepository.getSeller(idSeller);
            if (!seller.isPresent()) {
                System.out.println("No se encontró el vendedor con el ID proporcionado: " + idSeller);

            }
            return seller;
        } catch (EntityNotFoundException e) {
            System.out.println("Error al buscar el vendedor con ID " + idSeller + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new EntityNotFoundException("Vendedor no encontrado. Intente más tarde.");
        } catch (Exception e) {
            System.out.println("Error al buscar el vendedor con ID " + idSeller + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar el vendedor. Intente más tarde.", e);
        }
    }

    public Seller save(Seller seller) {
        try {
            return sellerRepository.save(seller);
        } catch (DataAccessException e) {
            System.out.println("Error al guardar el vendedor: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al guardar el vendedor. Intente más tarde.", e) {};
        }
    }

    public boolean delete(Integer idSeller) {
        try {
            Optional<Seller> seller = getSeller(idSeller);
            if (seller.isPresent()) {
                sellerRepository.delete(idSeller);
                return true;
            } else {
                System.out.println("No se encontró el vendedor con ID " + idSeller);
                return false;
            }
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar el vendedor con ID " + idSeller + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new DataAccessException("Error al eliminar el vendedor. Intente más tarde.", e) {};
        }
    }

    public List<Product> getProductsSoldBySeller(Integer idSeller) {
        try {


            Optional<List<Sale>> sales = saleRepository.getBySeller(idSeller);

            if (sales.isEmpty()) {
                System.out.println("No se encontraron ventas para el vendedor con ID " + idSeller);
                return new ArrayList<>();
            }

            List<Product> productsSold = new ArrayList<>();
            for (Sale sale : sales.get()) {
                Optional<Product> product = productRepository.getProduct(sale.getIdProduct());
                product.ifPresent(productsSold::add);
            }
            return productsSold;
        } catch (Exception e) {
            System.out.println("Error al obtener los productos vendidos por el vendedor con ID " + idSeller + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al obtener los productos vendidos. Intente más tarde.", e);
        }
    }

    public List<Float> getProductPricesSoldBySeller(Integer idSeller) {
        try {
            List<Product> productsSold = getProductsSoldBySeller(idSeller);
            List<Float> productPrices = new ArrayList<>();

            for (Product product : productsSold) {
                productPrices.add(product.getPrice());
            }

            return productPrices;
        } catch (Exception e) {
            System.out.println("Error al obtener los precios de los productos vendidos por el vendedor con ID " + idSeller + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al obtener los precios de los productos vendidos. Intente más tarde.", e);
        }
    }

    public float calculateCommissionForSeller(Integer idSeller) {
        try {
            List<Float> productPrices = getProductPricesSoldBySeller(idSeller);

            if (productPrices.isEmpty()) {
                return 0f;
            }

            float totalPrice = 0f;
            for (Float price : productPrices) {
                totalPrice += price;
            }

            return (productPrices.size() <= 2) ? totalPrice * 0.05f : totalPrice * 0.10f;
        } catch (Exception e) {
            System.out.println("Error al calcular la comisión para el vendedor con ID " + idSeller + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al calcular la comisión. Intente más tarde.", e);
        }
    }

    public float totalSalary(Integer idSeller) {
        try {
            Seller seller = sellerRepository.getSeller(idSeller)
                    .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

            float salary = seller.getSalary();
            float commission = calculateCommissionForSeller(idSeller);
            return salary + commission;
        } catch (Exception e) {
            System.out.println("Error al calcular el salario total para el vendedor con ID " + idSeller + ": " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al calcular el salario total. Intente más tarde.", e);
        }
    }
}
