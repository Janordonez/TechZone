package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;
import org.openxava.annotations.Tab;
import org.openxava.annotations.TextArea;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@Tab(properties = "nombre, telefono, correo, direccion")
public class Cliente extends BaseEntity{

    @Required
    @Column(length = 32)
    private String nombre;
    @Column(length = 8)
    private String telefono;
    @Email
    private String correo;
    @TextArea
    private String direccion;
}
