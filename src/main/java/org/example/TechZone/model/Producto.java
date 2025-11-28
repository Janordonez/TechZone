package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Money;
import org.openxava.annotations.Required;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Producto extends BaseEntity{

    @Required
    @Column(length = 32)
    private String nombre;
    private String marca;
    private String modelo;
    private int stock;
    @Money
    private BigDecimal precio;
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Categoria categoria;


    public void disminuirStock(int cantidad) {
        if (this.stock < cantidad) {
            // Esto lanzará un error amigable al usuario en OpenXava
            throw new javax.validation.ValidationException(
                    "No hay suficiente stock del producto " + nombre +
                            ". Stock actual: " + stock + ", Solicitado: " + cantidad
            );
        }
        this.stock = this.stock - cantidad;
    }
}
