package com.tienda.tienda.controller;

import com.tienda.tienda.domain.Product;
import com.tienda.tienda.domain.Seller;
import com.tienda.tienda.domain.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/all")
    public List<Seller> getAll() {
        return sellerService.getAll();
    }

    // Obtener un vendedor por ID
    @GetMapping("/{id}")
    public Optional<Seller> getSeller(@PathVariable("id") Integer idSeller) {
        return sellerService.getSeller(idSeller);
    }

    // Guardar un nuevo vendedor
    @PostMapping("/save")
    public Seller save(@RequestBody Seller seller) {
        return sellerService.save(seller);
    }

    // Eliminar un vendedor por ID
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer idSeller) {
        return sellerService.delete(idSeller);
    }

    @GetMapping("seller/{id}")
    public List<Product> getProductsSoldBySeller( @PathVariable("id")Integer idSeller){
        return sellerService.getProductsSoldBySeller(idSeller);
    }

    @GetMapping("price/{idSeller}")
    public List<Float> getProductPricesSoldBySeller(@PathVariable("idSeller") Integer idSeller){
        return sellerService.getProductPricesSoldBySeller(idSeller);
    }

    @GetMapping("com/{idSeller}")
    public float calculateCommissionForSeller(@PathVariable("idSeller")  Integer idSeller){
        return sellerService.calculateCommissionForSeller(idSeller);
    }

    @GetMapping("salary/{idSeller}")
    public float totalSalary(@PathVariable("idSeller") Integer idSeller){
        return sellerService.totalSalary(idSeller);
    }



}
