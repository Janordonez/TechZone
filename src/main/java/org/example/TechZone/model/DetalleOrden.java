package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.calculators.PrecioPorUnidad;
import org.example.TechZone.calculators.PrecioPorUnidadProveedor;
import org.openxava.annotations.Calculation;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.Money;
import org.openxava.annotations.PropertyValue;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class DetalleOrden {

    public int cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    public Producto producto;

    @DefaultValueCalculator(
            value = PrecioPorUnidadProveedor.class,
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
