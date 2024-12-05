package com.tienda.tienda.domain.service;

import com.tienda.tienda.domain.Product;
import com.tienda.tienda.domain.Sale;
import com.tienda.tienda.domain.Seller;
import com.tienda.tienda.domain.repository.ProductRepository;
import com.tienda.tienda.domain.repository.SaleRepository;
import com.tienda.tienda.domain.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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



    public List<Seller> getAll(){
        return sellerRepository.getAll();
    };

    public Optional<Seller> getSeller(Integer idSeller){
        return sellerRepository.getSeller(idSeller);
    };

    public Seller save(Seller seller){
        return sellerRepository.save(seller);
    }

    public boolean delete(Integer idseller){
        return getSeller(idseller).map(seller -> {
            sellerRepository.delete(idseller);
            return true;
        }).orElse(false);
    }

    public List<Product> getProductsSoldBySeller(Integer idSeller) {
        Optional<List<Sale>> sales = saleRepository.getBySeller(idSeller);

        if (sales.isEmpty()) {
            return new ArrayList<>();
        }

        // Extraer los productos de las ventas
        List<Product> productsSold = new ArrayList<>();
        for (Sale sale : sales.get()) {
            Optional<Product> product = productRepository.getProduct(sale.getIdProduct());
            product.ifPresent(productsSold::add);
        }

        return productsSold;
    }

    public List<Float> getProductPricesSoldBySeller(Integer idSeller) {
        List<Product> productsSold = getProductsSoldBySeller(idSeller);


        List<Float> productPrices = new ArrayList<>();

        for (Product product : productsSold) {
            productPrices.add(product.getPrice());
        }

        return productPrices;
    }

    public float calculateCommissionForSeller(Integer idSeller) {
        List<Float> productPrices = getProductPricesSoldBySeller(idSeller);

        if (productPrices.isEmpty()) {
            return 0f;
        }
        float totalPrice = 0f;
        for (Float price : productPrices) {
            totalPrice += price;
        }

        float commission = 0f;
        if (productPrices.size() <= 2) {
            commission = totalPrice * 0.05f;
        } else {
            commission = totalPrice * 0.10f;
        }
        return commission;
    }

    public float totalSalary(Integer idSeller) {
        Seller seller = sellerRepository.getSeller(idSeller).orElseThrow(() -> new RuntimeException("Seller not found"));
        float salary = seller.getSalary();
        float commission = calculateCommissionForSeller(idSeller);
        return salary + commission;
    }

}
