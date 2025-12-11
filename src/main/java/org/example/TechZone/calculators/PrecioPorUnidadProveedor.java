package org.example.TechZone.calculators;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.model.Producto;
import org.openxava.calculators.ICalculator;
import org.openxava.jpa.XPersistence;

public class PrecioPorUnidadProveedor implements ICalculator {

    @Getter
    @Setter
    String id;

    @Override
    public Object calculate() throws Exception {
        Producto producto = XPersistence.getManager().find(Producto.class, id);
        return producto.getPrecioProveedor();
    }
}
