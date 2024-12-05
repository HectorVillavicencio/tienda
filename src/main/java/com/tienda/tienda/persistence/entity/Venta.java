package com.tienda.tienda.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vendedor_codigo")
    private int idVendedor;

    @Column(name = "producto_codigo")
    private int idProducto;

    @ManyToOne
    @JoinColumn(name = "producto_codigo", insertable = false, updatable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "vendedor_codigo", insertable = false, updatable = false)
    private Vendedor vendedor;
}

