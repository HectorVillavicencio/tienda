package com.tienda.tienda.persistence.mapper;

import com.tienda.tienda.domain.Sale;
import com.tienda.tienda.persistence.entity.Venta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mappings({
            @Mapping(source = "id", target = "idSale"),
            @Mapping(source = "idVendedor", target = "idSeller"),
            @Mapping(source = "idProducto", target = "idProduct"),
    })
    Sale toSale(Venta venta);
    List<Sale> toSales(List<Venta> ventas);

    @InheritInverseConfiguration
    Venta toVenta(Sale sale);
}
