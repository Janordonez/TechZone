package org.example.TechZone.calculators;

import org.openxava.calculators.ICalculator;

import java.math.BigDecimal;

public class FondoInicialCalculator implements ICalculator {
    @Override
    public Object calculate() throws Exception {
        return new BigDecimal("1000");
    }
}

