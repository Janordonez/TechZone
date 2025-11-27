package org.example.TechZone.Actions;

import org.example.TechZone.model.Factura;
import org.openxava.actions.ViewBaseAction;
import org.openxava.jpa.XPersistence;
import org.openxava.model.MapFacade;

public class ConfirmarPedidoAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
        // 1. Obtenemos la entidad actual mapeada en la vista
        Factura factura = (Factura) MapFacade.findEntity(getView().getModelName(), getView().getKeyValues());

        // 2. Ejecutamos la lógica de negocio
        factura.confirmarFactura();

        // 3. Guardamos los cambios (esto dispara el update de stock en la DB)
        XPersistence.getManager().merge(factura);

        addMessage("Pedido confirmado y stock actualizado correctamente");
        getView().refresh();
    }
}
