package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
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
                    "producto.nombre, cantidad, producto.categoria.nombre, producto.precio," +
                            "subtotal+[" + "factura.ivaPorcentaje,"+ "factura.iva" + "factura.total" + "]"
            )
    Collection<Detalle> detalles;

    @Digits(integer = 2, fraction = 0)
    BigDecimal ivaPorcentaje;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.subtotal) * ivaPorcentaje / 100")
    BigDecimal iva;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.subtotal) + iva")
    BigDecimal total;
}
