package com.tienda.tienda.controller;

import com.tienda.tienda.domain.Sale;
import com.tienda.tienda.domain.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // Obtener todas las ventas
    @GetMapping("/all")
    public List<Sale> getAll() {
        return saleService.getAll();
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    public Optional<Sale> getSale(@PathVariable("id") Integer id) {
        return saleService.getSale(id);
    }

    // Guardar una nueva venta
    @PostMapping("/save")
    public Sale save(@RequestBody Sale sale) {
        return saleService.save(sale);
    }

    // Eliminar una venta por ID
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer idSale) {
        return saleService.delete(idSale);
    }

    // Obtener ventas por vendedor
    @GetMapping("/seller/{idVendedor}")
    public Optional<List<Sale>> getBySeller(@PathVariable("idVendedor") Integer idVendedor) {
        return saleService.getBySeller(idVendedor);
    }

    // Obtener ventas por producto
    @GetMapping("/product/{idProduct}")
    public Optional<List<Sale>> getByProduct(@RequestParam("idProduct") Integer idProduct) {
        return saleService.getByProduct(idProduct);
    }
}