package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;


@Embeddable
@Getter
@Setter
public class VistasPermitidas {
    @Enumerated
    private Vistas nombreVista;
}
