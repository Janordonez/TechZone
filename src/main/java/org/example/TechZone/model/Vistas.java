package org.example.TechZone.model;

import lombok.Getter;

@Getter
public enum Vistas {

    CATEGORIA("Categoria"),
    CLIENTE("Cliente"),
    EMPLEADO("Empleado"),
    FACTURAS("Factura"),
    OBJETIVOS("Objetivos"),
    PRODUCTOS("Producto"),
    ROL("Rol"),
    SESIONES_CAJA("SesionesCaja"),
    VENTAS("Ventas");

    private final String value;

    Vistas(String value) {
        this.value = value;
    }

}
