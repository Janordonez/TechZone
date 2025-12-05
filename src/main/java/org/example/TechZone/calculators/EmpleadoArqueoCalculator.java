package org.example.TechZone.calculators;

import org.example.TechZone.model.Empleado;
import org.openxava.calculators.ICalculator;
import org.openxava.jpa.XPersistence;
import org.openxava.util.Users;

public class EmpleadoArqueoCalculator implements ICalculator {

    @Override
    public Object calculate() throws Exception {
        String nombre = Users.getCurrent();

        return XPersistence.getManager().createQuery("SELECT e FROM Empleado e WHERE e.usuario = :nombre", Empleado.class).setParameter("nombre", nombre).getSingleResult();
    }
}
