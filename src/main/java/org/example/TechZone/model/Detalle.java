package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.calculators.PrecioPorUnidadCalc;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.Depends;
import org.openxava.annotations.Money;
import org.openxava.annotations.PropertyValue;

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

    @Money
    @Depends("precioPorUnidad, cantidad")
    public BigDecimal getSubtotal(){
        if(precioPorUnidad == null){
            return BigDecimal.ZERO;
        }
        return new BigDecimal(cantidad).multiply(precioPorUnidad);
    }

    @DefaultValueCalculator(
            value = PrecioPorUnidadCalc.class,
            properties = @PropertyValue(
                    name = "numeroProducto",
                    from = "producto.numero")
    )
    @Money
    public BigDecimal precioPorUnidad;
}
