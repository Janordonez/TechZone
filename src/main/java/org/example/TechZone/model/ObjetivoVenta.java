package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;
import org.openxava.annotations.View;
import org.openxava.annotations.Tab;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@View(members =
        "fechaInicio, fechaFin; " +
                "metaVentas; " +
                "cumplido"
)
@Tab(properties = "fechaInicio, fechaFin, metaVentas, cumplido")
public class ObjetivoVenta extends BaseEntity {

    @Required
    private LocalDate fechaInicio;

    @Required
    private LocalDate fechaFin;

    @Required
    private BigDecimal metaVentas;

    private boolean cumplido;
}
