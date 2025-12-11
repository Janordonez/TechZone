package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.calculators.PorcentajeIVACalculator;
import org.openxava.annotations.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Ordenes extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Proveedor proveedor;

    @ElementCollection
    @ListProperties(
            "producto.nombre, cantidad, producto.categoria.nombre, precioPorUnidad, subtotal+ [" +
                    "ordenes.porcentajeIVA, ordenes.iva, ordenes.importeTotal, ordenes.ventaNeta]"
    )
    Collection<DetalleOrden> detalles;

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
