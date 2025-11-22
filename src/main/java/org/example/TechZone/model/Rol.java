package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.TextArea;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Rol extends BaseEntity{
    private String nombreRol;
    @TextArea
    private String descripcion;
    @ElementCollection
            @ListProperties("nombreVista")
    Collection<VistasPermitidas> vistas;
}
