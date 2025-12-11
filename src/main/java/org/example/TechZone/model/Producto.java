package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@View(members = "nombre;" + "marca, modelo;" + "stock, precio, precioProveedor;" + "imagenes; categoria, proveedor;")
@Tab(properties = "nombre, marca, modelo, stock, precio, precioProveedor, categoria.nombre")
public class Producto extends BaseEntity{

    @Required
    @Column(length = 32)
    private String nombre;
    private String marca;
    private String modelo;
    private int stock;
    @Money
    @Required
    private BigDecimal precio;
    @Money
    @Required
    private BigDecimal precioProveedor;
    @Files
    private String imagenes;
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreFiscal")
    private Proveedor proveedor;



}
