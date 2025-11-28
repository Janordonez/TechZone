package org.example.TechZone.Actions; // Ajusta el paquete

import org.example.TechZone.model.Ventas;
import org.openxava.actions.*;

public class ActualizarFacturas extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
        Ventas ventas = (Ventas) getView().getEntity();

        // Actualizamos la lista de facturas
        getView().setValue("facturaList", ventas.getFacturaList());

        // Actualizamos el campo ventaBruta
        getView().setValue("ventaBruta", ventas.getVentaBruta());

        getView().setValue("ventaNeta", ventas.getVentaNeta());

        getView().setValue("impuestos", ventas.getImpuestos());

        getView().setValue("numeroFacturas",ventas.getNumeroFacturas());
    }
}