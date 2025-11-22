package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.DescriptionsList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Empleado extends BaseEntity{
    private String nombre;
    private String usuario;
    private String contrasena;
    private String direccion;
    private String telefono;
    private String correo;
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreRol")
    private Rol rol;
}
