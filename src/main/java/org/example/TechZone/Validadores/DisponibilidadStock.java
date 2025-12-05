package org.example.TechZone.Validadores;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.model.Producto;
import org.openxava.util.Messages;
import org.openxava.validators.IValidator;

@Getter
@Setter
public class DisponibilidadStock implements IValidator {

    private int cantidad;
    private Producto producto;

    @Override
    public void validate(Messages messages) throws Exception {
        if(cantidad > producto.getStock()){
            messages.add("disponibilidad_stock");
        }
    }
}
