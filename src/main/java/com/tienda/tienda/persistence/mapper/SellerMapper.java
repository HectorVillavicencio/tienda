package com.tienda.tienda.persistence.mapper;

import com.tienda.tienda.domain.Seller;
import com.tienda.tienda.persistence.entity.Producto;
import com.tienda.tienda.persistence.entity.Vendedor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    @Mappings({
            @Mapping(source = "id", target = "idSeller"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "sueldo", target = "salary"),
    })
    Seller toSeller(Vendedor vendedor);
    List<Seller> toSellers(List<Vendedor> vendedores);

    @InheritInverseConfiguration
    @Mapping(target = "ventas",ignore = true)
    Vendedor toVendedor(Seller seller);

    @Named("mapVendedorToId")
    default int mapVendedorToId(Vendedor vendedor) {
        return vendedor != null ? vendedor.getId() : 0; // Extrae el id de Vendedor
    }

    @Named("mapProductoToId")
    default int mapProductoToId(Producto producto) {
        return producto != null ? producto.getId() : 0; // Extrae el id de Producto
    }
}
