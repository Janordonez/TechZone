package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.calculators.FechaFinal;
import org.example.TechZone.calculators.FechaInicial;
import org.example.TechZone.calculators.FondoInicialCalculator;
import org.example.TechZone.calculators.MontoRegistradoCalculator;
import org.openxava.annotations.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@View(members = "empleado; fechaInicio, fechaFin; fondoInicial, montoDeclarado, montoRegistrado; cerrado;")
public class SesionesCaja extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @DescriptionsList(descriptionProperties = "nombre")
    private Empleado empleado;

    @ReadOnly
    @DefaultValueCalculator(FechaInicial.class)
    private LocalDateTime fechaInicio;
    @ReadOnly
    @DefaultValueCalculator(FechaFinal.class)
    private LocalDateTime fechaFin;

    @Money
    @DefaultValueCalculator(FondoInicialCalculator.class)
    @ReadOnly
    private BigDecimal fondoInicial;
    @Money
    private BigDecimal montoDeclarado;
    @ReadOnly
    @DefaultValueCalculator(
            value = MontoRegistradoCalculator.class,
            properties = {@PropertyValue(name = "fecha", from = "fechaInicio"),
                    @PropertyValue(name = "id", from = "empleado.id")
            }
    )
    private BigDecimal montoRegistrado;
    @ReadOnly
    private BigDecimal diferencia;

    private Boolean cerrado;
}
