package org.example.TechZone.Actions;

import org.example.TechZone.model.Detalle;
import org.example.TechZone.model.Producto;
import org.openxava.actions.SaveAction;
import org.openxava.jpa.XPersistence;
import org.openxava.model.MapFacade;
import org.openxava.view.View;

import javax.ejb.DuplicateKeyException;
import javax.ejb.ObjectNotFoundException;
import javax.management.Query;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomSaveAction extends SaveAction {

    @Override
    public void execute() throws Exception{
        try {
            if (getView().isKeyEditable()) {
                Map values = create();
                View listaColeccion = getView().getSubview("detalles");
                for (Map<String, Object> o : listaColeccion.getCollectionValues()){
                    Map<String, String> producto = (Map<String, String>) o.get("producto");
                    Producto productoBd = XPersistence.getManager()
                            .createQuery("SELECT p FROM Producto p WHERE p.id = : id", Producto.class)
                            .setParameter("id", producto.get("id")).getSingleResult();
                    productoBd.setStock(productoBd.getStock() - (int) o.get("cantidad"));
                    XPersistence.getManager().merge(productoBd);

                }
                updateView(values, isResetAfterOnCreate());

             }
            else {
                Map values = modify();
                updateView(values, isResetAfterOnModify());
            }

            resetDescriptionsCache();
        }
        catch (ValidationException ex) {
            //addErrors(ex.getErrors());
        }
        catch (ObjectNotFoundException ex) {
            addError("no_modify_no_exists");
        }
        catch (DuplicateKeyException ex) {
            addError("no_create_exists");
        }
    }
}
