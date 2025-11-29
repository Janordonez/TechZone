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
@View(members = "nombre;" + "marca, modelo;" + "stock, precio;" + "imagenes; categoria;")
public class Producto extends BaseEntity{

    @Required
    @Column(length = 32)
    private String nombre;
    private String marca;
    private String modelo;
    private int stock;
    @Money
    private BigDecimal precio;
    @Files
    private String imagenes;
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Categoria categoria;


}
