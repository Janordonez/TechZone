package org.example.TechZone.Actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.example.TechZone.model.Empleado;
import org.openxava.actions.ModifyFromReferenceAction;
import org.openxava.jpa.XPersistence;
import org.openxava.util.Users;

import java.util.Map;

public class NewEditFromReference extends ModifyFromReferenceAction {

    private static Log log = LogFactory.getLog(ModifyFromReferenceAction.class);

    private boolean exists = true;

    @Override
    public void execute() throws Exception {
        String user = Users.getCurrent();
        Empleado empleado = XPersistence.getManager().createQuery("SELECT e FROM Empleado e WHERE e.usuario = :user", Empleado.class).setParameter("user", user).getSingleResult();

        if(empleado.getRol().getVistas().contains(getModelName())){
            super.execute();

            Map key = getReferenceSubview().getKeyValuesWithValue();
            if (key.isEmpty()) {
                closeDialog();
                exists = false;
                addError("cannot_modify_empty_reference");
                return;
            }

            getView().setKeyEditable(false);
            getView().setValues(key);
        }else{
            addError("No tiene permisas para crearlo");
        }
    }

    public String getCustomController() {
        return getModel() + "Modification";
    }

    public String getDefaultController() {
        return "Modification";
    }

    public String[] getNextControllers() throws Exception {
        return exists?super.getNextControllers():SAME_CONTROLLERS;
    }

    public String getNextAction() throws Exception {
        return exists?getController() + ".search":null;
    }
}
