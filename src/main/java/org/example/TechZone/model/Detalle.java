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
@EntityValidator(
        value = org.example.TechZone.Validadores.DisponibilidadStock.class,
        properties = {
                @PropertyValue(name = "cantidad", from = "cantidad"),
                @PropertyValue(name ="producto", from = "producto")
        }
)
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
