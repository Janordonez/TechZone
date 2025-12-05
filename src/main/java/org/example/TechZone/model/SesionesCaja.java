package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.Validadores.SesionDeCajaValidator;
import org.example.TechZone.calculators.*;
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
@EntityValidator(value = SesionDeCajaValidator.class)
public class SesionesCaja extends BaseEntity{

    @DefaultValueCalculator(EmpleadoArqueoCalculator.class)
    @ManyToOne(fetch = FetchType.LAZY)
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
                    @PropertyValue(name = "id", from = "empleado.id"),
                    @PropertyValue(name = "fondoInicial", from = "fondoInicial")
            }
    )
    @Depends(value = "empleado")
    private BigDecimal montoRegistrado;

    @ReadOnly
    private BigDecimal diferencia;

    @ReadOnly
    private Boolean cerrado;
}
