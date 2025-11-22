package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class Detalle {
    private int cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    private BigDecimal precioPorUnidad;

    public BigDecimal getSubTotal(){
        if(precioPorUnidad == null){
            return BigDecimal.ZERO;
        }
        return precioPorUnidad.multiply(new BigDecimal(cantidad));
    }
}
