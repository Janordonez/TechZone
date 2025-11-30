package org.example.TechZone.calculators;

import lombok.Getter;
import lombok.Setter;
import org.openxava.calculators.ICalculator;
import org.openxava.jpa.XPersistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MontoRegistradoCalculator implements ICalculator {

    @Getter
    @Setter
    LocalDateTime fecha;

    @Getter
    @Setter
    String id;

    @Getter
    @Setter
    BigDecimal fondoInicial;

    @Override
    public Object calculate() throws Exception {
        LocalDate fechaDia = fecha.toLocalDate();
        BigDecimal monto = XPersistence.getManager()
                .createQuery("SELECT sum(f.importeTotal) FROM Factura f " +
                        "WHERE f.fecha = :fecha AND f.empleado.id = :id" ,BigDecimal.class)
                .setParameter("fecha", fechaDia)
                .setParameter("id", id)
                .getSingleResult();


        return (monto == null) ? BigDecimal.ZERO : monto;
    }
}
