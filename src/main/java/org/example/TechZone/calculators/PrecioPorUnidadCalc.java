package org.example.TechZone.calculators;

import static org.openxava.jpa.XPersistence.getManager;
import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.model.Producto;
import org.openxava.calculators.ICalculator;

public class PrecioPorUnidadCalc implements ICalculator {
    @Getter
    @Setter
    int numeroProducto;

    @Override
    public Object calculate() throws Exception {
        Producto producto = getManager().find(Producto.class, numeroProducto);
        return producto.getPrecio();
    }
}
