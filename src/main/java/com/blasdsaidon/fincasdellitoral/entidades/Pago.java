/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.Collection;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
@Entity
public class Pago {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idPago;
    private String mesAno;
    private Boolean realizado;
    @OneToOne(cascade={CascadeType.REMOVE}) 
    private Recibo recibo;
    private int numeroCuota;
    private Double interesesPuni;
    private Double descuentoHono;

    public Pago(String idPago, String mesAno, Boolean realizado, Recibo recibo, int numeroCuota, Double interesesPuni, Double descuentoHono, Double importe, Double importeAgua, Double importeTasa, Collection<Otros> otros) {
        this.idPago = idPago;
        this.mesAno = mesAno;
        this.realizado = realizado;
        this.recibo = recibo;
        this.numeroCuota = numeroCuota;
        this.interesesPuni = interesesPuni;
        this.descuentoHono = descuentoHono;
        this.importe = importe;
        this.importeAgua = importeAgua;
        this.importeTasa = importeTasa;
        this.otros = otros;
    }
    
    private Double importe;
    private Double importeAgua;
    private Double importeTasa;
    @OneToMany(cascade={CascadeType.REMOVE}) 
    private Collection<Otros> otros;

    
    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public void setImporteAgua(Double importeAgua) {
        this.importeAgua = importeAgua;
    }

    public void setImporteTasa(Double importeTasa) {
        this.importeTasa = importeTasa;
    }

    public Double getInteresesPuni() {
        return interesesPuni;
    }

    public void setInteresesPuni(Double interesesPuni) {
        this.interesesPuni = interesesPuni;
    }

    public Double getDescuentoHono() {
        return descuentoHono;
    }

    public void setDescuentoHono(Double descuentoHono) {
        this.descuentoHono = descuentoHono;
    }

    

    public Double getImporte() {
        return importe;
    }

    public Double getImporteAgua() {
        return importeAgua;
    }

    public Double getImporteTasa() {
        return importeTasa;
    }

    public Pago(String idPago, String mesAno, Boolean realizado, Recibo recibo, int numeroCuota,  Double importe, Double importeAgua, Double importeTasa, Collection<Otros> otros) {
        this.idPago = idPago;
        this.mesAno = mesAno;
        this.realizado = realizado;
        this.recibo = recibo;
        this.numeroCuota = numeroCuota;
        
        this.importe = importe;
        this.importeAgua = importeAgua;
        this.importeTasa = importeTasa;
        this.otros = otros;
    }

    public void setOtros(Collection<Otros> otros) {
        this.otros = otros;
    }

    public Collection<Otros> getOtros() {
        return otros;
    }


    public Pago() {
    }

    public String getIdPago() {
        return idPago;
    }

    public String getMesAno() {
        return mesAno;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    @Override
    public String toString() {
        
                return  "{"
            + "\"mesAno\": \"" + mesAno + "\", "
            + "\"realizado\": " + realizado + ", "
            + "\"importe\": " + importe + ", "
            + "\"importeAgua\": " + importeAgua + ", "
            + "\"importeTasa\": " + importeTasa + ", "
            + "\"numeroCuota\": " + numeroCuota
            + "}";
    }

   
    
    
}
