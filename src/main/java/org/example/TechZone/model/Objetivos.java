package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.calculators.ModalidadObjetivoCalculator;
import org.example.TechZone.calculators.PorcentajeObjetivoCalculator;
import org.example.TechZone.calculators.VentasEntreFechasCalculator;
import org.openxava.actions.BaseAction;
import org.openxava.annotations.*;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter

public class Objetivos extends BaseEntity {
    @Required
    private LocalDate fechaInicio;
    @Required
    private LocalDate fechaFinal;

    @ReadOnly
    @DefaultValueCalculator(
            value = ModalidadObjetivoCalculator.class,
            properties = {
                    @PropertyValue(name = "fechaInicio", from = "fechaInicio"),
                    @PropertyValue(name = "fechaFinal", from = "fechaFinal")
            }
    )
    @Depends("fechaInicio, fechaFinal")
    private String modalidad;

    @Money
    @Required
    private BigDecimal metaVentas;

    @ReadOnly
    @DefaultValueCalculator(
            value = VentasEntreFechasCalculator.class,
            properties = {
                    @PropertyValue( name = "fechaInicio", from = "fechaInicio"),
                    @PropertyValue( name = "fechaFinal", from = "fechaFinal")
            }
    )
    @Depends("fechaInicio, fechaFinal")
    private BigDecimal ventasAcumuladas;

    @ReadOnly
    @DefaultValueCalculator(
            value = PorcentajeObjetivoCalculator.class,
            properties = {
                    @PropertyValue( name = "ventasAcumuladas", from = "ventasAcumuladas"),
                    @PropertyValue( name = "metaVentas", from = "metaVentas")
            }
    )
    @Depends("ventasAcumuladas, metaVentas")
    private BigDecimal porcentajeAvance;

    @ReadOnly
    @Depends( "porcentajeAvance")
    public boolean isCumplido() {
        return porcentajeAvance != null && porcentajeAvance.compareTo(new BigDecimal("100")) >= 0;
    }
}




