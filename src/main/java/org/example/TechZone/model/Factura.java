package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.calculators.PorcentajeIVACalculator;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;
    private LocalDate fecha;
    private TipoDePago tipoDePago;

    @ElementCollection
    @ListProperties(
            "producto.nombre, cantidad, producto.categoria.nombre, precioPorUnidad, subtotal+ [" +
                    "factura.porcentajeIVA, factura.iva, factura.importeTotal, factura.ventaNeta]"
    )
    Collection<Detalle> detalles;

    @ReadOnly
    @Digits(integer=2, fraction=0)
    @DefaultValueCalculator(PorcentajeIVACalculator.class)
    BigDecimal porcentajeIVA;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.subtotal) * porcentajeIVA / 100")
    BigDecimal iva;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.subtotal) + iva")
    BigDecimal importeTotal;

    @ReadOnly
    @Money
    @Calculation("importeTotal - iva")
    BigDecimal ventaNeta;



}
