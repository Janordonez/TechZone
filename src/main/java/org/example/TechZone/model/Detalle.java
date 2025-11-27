package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;

import org.example.TechZone.calculators.PrecioPorUnidad;
import org.openxava.annotations.*;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class Detalle {
    public int cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    public Producto producto;

    @DefaultValueCalculator(
            value = PrecioPorUnidad.class,
            properties = @PropertyValue(
                    name = "id",
                    from = "producto.id"
            )
    )
    @Money
    BigDecimal precioPorUnidad;

    @Money
    @Calculation("precioPorUnidad * cantidad")
    BigDecimal subtotal;



}
