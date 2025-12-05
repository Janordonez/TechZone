package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Required;
import org.openxava.annotations.Tab;
import org.openxava.annotations.TextArea;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collection;

@Entity
@Getter
@Setter
@Tab(properties = "nombreRol, descripcion")
public class Rol extends BaseEntity{

    @Required
    @Column(length = 32)
    private String nombreRol;
    @TextArea
    private String descripcion;
    @ElementCollection
    @ListProperties("nombreVista")
    Collection<VistasPermitidas> vistas;
}
