package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Label;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Factura extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    private LocalDate fecha;
    private TipoDePago tipoDePago;
    @ElementCollection
            @ListProperties(
                    "producto.nombre" + ",cantidad" + ",producto.categoria.nombre" + ",producto.precio" + ",getSubtotal()"
            )
    Collection<Detalle> detalles;

    @Money
    private BigDecimal iva;
    @Money
    private BigDecimal subTotal;
    @Money
    private BigDecimal total;
}
