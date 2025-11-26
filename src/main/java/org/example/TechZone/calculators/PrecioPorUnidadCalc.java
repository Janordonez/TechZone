package org.example.TechZone.calculators;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.model.Producto;
import org.openxava.calculators.ICalculator;
import org.openxava.jpa.XPersistence;

public class PrecioPorUnidadCalc implements ICalculator {
    @Getter
    @Setter
    int numeroProducto;

    @Override
    public Object calculate() throws Exception {
        Producto producto = XPersistence.getManager().find(Producto.class, numeroProducto);
        return producto.getPrecio();
    }
}
