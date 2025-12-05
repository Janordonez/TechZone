package org.example.TechZone.Actions;

import org.example.TechZone.model.Empleado;
import org.openxava.actions.CreateNewFromReferenceAction;
import org.openxava.jpa.XPersistence;
import org.openxava.util.Users;

public class NewCreateFromReference extends CreateNewFromReferenceAction {

    @Override
    public void execute() throws Exception {
        String user = Users.getCurrent();
        Empleado empleado = XPersistence.getManager().createQuery("SELECT e FROM Empleado e WHERE e.usuario = :user", Empleado.class).setParameter("user", user).getSingleResult();

        if(empleado.getRol().getVistas().contains(getModelName())){
            super.execute();
        }else{
            addError("No tiene permisas para crear un " + getModelName());
        }
    }
}
