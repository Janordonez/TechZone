package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Money;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Producto extends BaseEntity{

    private String nombre;
    private String modelo;
    private String marca;
    private int stock;
    @Money
    private BigDecimal precio;
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Categoria categoria;
}
