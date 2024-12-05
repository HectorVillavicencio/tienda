package com.tienda.tienda.persistence;

import com.tienda.tienda.domain.Seller;
import com.tienda.tienda.domain.repository.SellerRepository;
import com.tienda.tienda.persistence.crud.VendedorCrudRepository;
import com.tienda.tienda.persistence.entity.Vendedor;
import com.tienda.tienda.persistence.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VendedorRepository implements SellerRepository {

    @Autowired
    private VendedorCrudRepository vendedorCrudRepository;

    @Autowired
    private SellerMapper mapper;

    @Override
    public List<Seller> getAll() {
        try {
            List<Vendedor> vendedores = (List<Vendedor>) vendedorCrudRepository.findAll();
            return mapper.toSellers(vendedores);
        } catch (Exception e) {
            System.out.println("Error al obtener todos los vendedores: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al obtener todos los vendedores. Intente más tarde.", e);
        }
    }

    @Override
    public Optional<Seller> getSeller(int idSeller){
        try {
            Optional<Seller> seller = vendedorCrudRepository.findById(idSeller).map(vendedor -> mapper.toSeller(vendedor));

            if (!seller.isPresent()) {
                System.out.println("No se encontró al vendor con el código proporcionado");
            }

            return seller;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al buscar al vendedor. Intente más tarde.", e);
        }
    }

    @Override
    public Seller save(Seller seller) {
        try {
            Vendedor vendedor = mapper.toVendedor(seller);
            return mapper.toSeller(vendedorCrudRepository.save(vendedor));
        } catch (Exception e) {
            System.out.println("Error al guardar al vendedor: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al guardar al vendedor. Intente más tarde.", e);
        }
    }

    @Override
    public void delete(int codigo) {
        try {
            vendedorCrudRepository.deleteById(codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar al vendedor con el código proporcionado: " + e.getMessage());
            e.printStackTrace(System.out);
            throw new RuntimeException("Error al eliminar al vendedor. Intente más tarde.", e);
        }
    }

}
