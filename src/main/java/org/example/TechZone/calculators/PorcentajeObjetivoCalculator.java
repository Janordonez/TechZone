package org.example.TechZone.calculators;

import lombok.Getter;
import lombok.Setter;
import org.openxava.calculators.ICalculator;

import java.math.BigDecimal;

@Getter
@Setter

public class PorcentajeObjetivoCalculator implements ICalculator {
    private BigDecimal ventasAcumuladas;
    private BigDecimal metaVentas;

    @Override

    public Object calculate() throws  Exception {

        if (metaVentas == null || metaVentas.compareTo(BigDecimal.ZERO) == 0)
            return  BigDecimal.ZERO;

       if (ventasAcumuladas == null)
           ventasAcumuladas = BigDecimal.ZERO;

       return ventasAcumuladas
               .multiply(BigDecimal.valueOf(100))
               .divide(metaVentas, 2, BigDecimal.ROUND_HALF_UP);}
    }

