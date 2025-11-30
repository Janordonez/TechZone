package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
public class Empleado extends BaseEntity{

    @Required
    @Column(length = 32)
    private String nombre;
    @Required
    @Column(length = 32)
    private String usuario;
    @Required
    @Column(length = 32)
    private String contrasena;
    @TextArea
    private String direccion;
    @Telephone
    private String telefono;
    @Email
    private String correo;
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList(descriptionProperties = "nombreRol")
    private Rol rol;


}
