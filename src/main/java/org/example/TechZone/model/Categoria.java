package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.TextArea;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Categoria extends BaseEntity{

    private String nombre;
    @TextArea
    private String descripcion;
}
