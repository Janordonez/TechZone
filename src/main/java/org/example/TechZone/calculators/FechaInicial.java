package org.example.TechZone.calculators;

import org.openxava.calculators.ICalculator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FechaInicial implements ICalculator {

    @Override
    public Object calculate() throws Exception {

        LocalDate localDate = java.time.LocalDate.now();
        LocalTime localTime = LocalTime.of(7, 0);
        return LocalDateTime.of(localDate, localTime);
    }
}
