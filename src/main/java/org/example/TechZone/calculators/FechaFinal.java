package org.example.TechZone.calculators;

import org.openxava.calculators.ICalculator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FechaFinal implements ICalculator {
    @Override
    public Object calculate() throws Exception {
        LocalDate localDate = java.time.LocalDate.now();
        LocalTime localTime = LocalTime.of(18, 0);
        return LocalDateTime.of(localDate, localTime);
    }
}
