package com.tienda.tienda.persistence;


import com.tienda.tienda.domain.Sale;
import com.tienda.tienda.domain.repository.SaleRepository;
import com.tienda.tienda.persistence.crud.VentaCrudRepository;
import com.tienda.tienda.persistence.entity.Venta;
import com.tienda.tienda.persistence.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VentaRepository implements SaleRepository {

    @Autowired
    private VentaCrudRepository ventaCrudRepository;

    @Autowired
    private SaleMapper mapper;

    @Override
    public List<Sale> getAll(){
        try {
            List<Venta> ventas = (List<Venta>) ventaCrudRepository.findAll();
            if (ventas.isEmpty()) {
                System.out.println("No se encontraron ventas");
            }
            return mapper.toSales(ventas);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar las ventas. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<Sale> getSale(int idSale){
        try {
            Optional<Sale> sale = ventaCrudRepository.findById(idSale).map(venta -> mapper.toSale(venta));

            if (sale.isEmpty()) {
                System.out.println("No se encontró la venta con el código proporcionado");
            }

            return sale;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar la venta. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<List<Sale>> getBySeller(int seller) {
        try {
            List<Venta> ventas = ventaCrudRepository.findByIdVendedor(seller);
            if (ventas.isEmpty()) {
                System.out.println("No se encontraron ventas para el vendedor " + seller);
            }
            return Optional.of(mapper.toSales(ventas));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar las ventas para el vemdedpr. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<List<Sale>> getByProduct(int product){
        try {
            List<Venta> ventas = ventaCrudRepository.findByIdProducto(product);
            if (ventas.isEmpty()) {
                System.out.println("No se encontraron ventas para el producto " + product);
            }
            return Optional.of(mapper.toSales(ventas));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar las ventas para el producto. Intente más tarde.", e);
        }
    }

    @Override
    public Sale save(Sale sale) {
        try {
            Venta venta =  mapper.toVenta(sale);
            return mapper.toSale(ventaCrudRepository.save(venta));
        } catch (Exception e) {
            System.out.println("Error al guardar a la venta: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al guardar a la venta. Intente más tarde.", e);
        }
    }

    @Override
    public void delete(int codigo) {
        try {
            ventaCrudRepository.deleteById(codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar la venta con el código proporcionado: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al eliminar la venta. Intente más tarde.", e);
        }
    }

}
