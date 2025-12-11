package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Telephone;
import org.openxava.annotations.TextArea;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Proveedor extends BaseEntity{

    private String nombreFiscal;
    private String nombreComercial;
    private String numeroRUC;
    @TextArea
    private String direccion;
    @Telephone
    private String telefono;
    @Email
    private String correoElectronico;
    private LocalDate fechaRegistro;

}
