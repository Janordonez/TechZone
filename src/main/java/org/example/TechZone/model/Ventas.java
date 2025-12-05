package org.example.TechZone.model;

import lombok.Getter;
import lombok.Setter;
import org.example.TechZone.Actions.ActualizarFacturas;
import org.openxava.annotations.*;
import org.openxava.jpa.XPersistence;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Entity
@Getter
@Setter
public class    Ventas extends BaseEntity{

    @OnChange(ActualizarFacturas.class)
    private LocalDate fechaInicio;
    @OnChange(ActualizarFacturas.class)
    private LocalDate fechaFin;

    @ReadOnly
    @Transient
    @ListProperties("cliente.nombre, fecha, importeTotal")
    private Collection<Factura> facturaList;

    public Collection<Factura> getFacturaList(){

        if(fechaInicio == null || fechaFin == null){
            return new ArrayList<>();
        }

        TypedQuery<Factura> query = XPersistence.getManager()
                .createQuery("SELECT f FROM Factura f " +
                        "WHERE f.fecha BETWEEN :fechaInicio AND :fechaFin", Factura.class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);


        return query.getResultList();
    }

    @Money
    @ReadOnly
    @Transient
    BigDecimal ventaBruta;

    public BigDecimal getVentaBruta() {
        if(getFacturaList() == null){
            System.out.println("No se econtro registros");
        }
        return getFacturaList().stream()
                .map(Factura::getImporteTotal).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Money
    @ReadOnly
    BigDecimal ventaNeta;

    public BigDecimal getVentaNeta() {
        if(getFacturaList() == null){
            System.out.println("No se econtro registros");
        }
        return getFacturaList().stream()
                .map(Factura::getVentaNeta).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Money
    @ReadOnly
    BigDecimal impuestos;

    public BigDecimal getImpuestos() {
        if(getFacturaList() == null){
            System.out.println("No se econtro registros");
        }
        return getFacturaList().stream()
                .map(Factura::getIva).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @ReadOnly
    int numeroFacturas;
    public int getNumeroFacturas() {
        if(getFacturaList() == null){
            System.out.println("No se econtro registros");
        }
        return getFacturaList().size();
    }

    @Money
    @ReadOnly
    Double gastoPromedio;
    public Double getGastoPromedio(){
        if(getFacturaList() == null){
            return 0.00;
        }
        return getFacturaList().stream().map(Factura::getImporteTotal).filter(Objects::nonNull).mapToDouble(BigDecimal::doubleValue).average().orElse(0.0);
    }

}
