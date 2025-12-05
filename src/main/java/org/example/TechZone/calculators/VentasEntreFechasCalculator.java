package org.example.TechZone.calculators;

import lombok.Getter;
import lombok.Setter;
import org.openxava.calculators.ICalculator;
import org.openxava.jpa.XPersistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class VentasEntreFechasCalculator implements ICalculator {

    LocalDate fechaInicio;
    LocalDate fechaFinal;

    @Override
    public Object calculate() throws Exception {
        if (fechaInicio == null || fechaFinal == null) return BigDecimal.ZERO;

        LocalDateTime inicio = fechaInicio.atStartOfDay();
        LocalDateTime fin = fechaFinal.atTime(23,59,59);

        BigDecimal total = (BigDecimal) XPersistence.getManager()
                .createQuery("SELECT COALESCE(SUM(s.montoRegistrado),0) " +
                        "FROM SesionesCaja s " +
                        "WHERE s.fechaInicio >= :inicio AND s.fechaFin <= :fin ")
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .getSingleResult();
        return total;
    }
}
