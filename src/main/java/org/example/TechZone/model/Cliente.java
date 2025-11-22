package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Cliente extends BaseEntity{
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
}
