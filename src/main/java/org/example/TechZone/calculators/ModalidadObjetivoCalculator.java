package org.example.TechZone.calculators;

import lombok.Getter;
import lombok.Setter;
import org.openxava.calculators.ICalculator;

import java.time.LocalDate;

@Getter
@Setter
public class ModalidadObjetivoCalculator implements ICalculator {

    LocalDate fechaInicio;
    LocalDate fechaFinal;

    @Override
    public Object calculate() throws Exception {
        if (fechaInicio == null || fechaFinal == null) return "Indefinido";

        int dias = fechaInicio.until(fechaFinal).getDays() + 1;

        if (dias == 7) { return "Semanal"; }
        if (dias >= 28 && dias <= 31) { return "Mensual"; }
        return "Personalizado";

    }

}
