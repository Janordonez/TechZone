package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;
import org.openxava.annotations.TextArea;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Categoria extends BaseEntity{

    @Required
    @Column(length = 32)
    private String nombre;
    @TextArea
    private String descripcion;
}
