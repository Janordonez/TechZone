package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Ventas extends BaseEntity{
    private LocalDate fecha;
    private int ventasPorProducto;
    private int ventasTotales;
    @OneToMany(mappedBy = "id")
    private Set<Factura> factura;
}
