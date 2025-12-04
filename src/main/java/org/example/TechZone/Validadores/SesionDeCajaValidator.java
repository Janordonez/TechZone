package org.example.TechZone.Validadores;

import lombok.Getter;
import lombok.Setter;
import org.openxava.util.Messages;
import org.openxava.validators.IValidator;

import java.time.LocalTime;


public class SesionDeCajaValidator implements IValidator {

    @Getter
    @Setter
    LocalTime corteTiempo = LocalTime.of(18, 30);

    @Override
    public void validate(Messages messages) throws Exception {

        if(LocalTime.now().isAfter(corteTiempo)) {
            messages.add("Caja cerrada!");
        }

    }
}
