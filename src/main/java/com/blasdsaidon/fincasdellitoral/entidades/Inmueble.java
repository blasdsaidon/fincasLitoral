/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
@Entity
public class Inmueble {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idInm;
    @OneToOne
    private Domicilio domicilio; 
    private String numPartida;
    private String numTGI;
    private String numTOS;
    private String titulares;
    private String numRegPropiedad;
    private String tomo;
    private String folio;
    private String fechaRegProp;
    @Column(length = 500)
    private String notas;

    public Inmueble() {
    }

    public Inmueble(String idInm, Domicilio domicilio, String numPartida, String numTGI, String numTOS, String titulares, String numRegPropiedad, String tomo, String folio, String fechaRegProp, String notas) {
        this.idInm = idInm;
        this.domicilio = domicilio;
        this.numPartida = numPartida;
        this.numTGI = numTGI;
        this.numTOS = numTOS;
        this.titulares = titulares;
        this.numRegPropiedad = numRegPropiedad;
        this.tomo = tomo;
        this.folio = folio;
        this.fechaRegProp = fechaRegProp;
        this.notas = notas;
    }

    public String getIdInm() {
        return idInm;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public String getNumPartida() {
        return numPartida;
    }

    public String getNumTGI() {
        return numTGI;
    }

    public String getNumTOS() {
        return numTOS;
    }

    public String getTitulares() {
        return titulares;
    }

    public String getNumRegPropiedad() {
        return numRegPropiedad;
    }

    public String getTomo() {
        return tomo;
    }

    public String getFolio() {
        return folio;
    }

    public String getFechaRegProp() {
        return fechaRegProp;
    }

    public String getNotas() {
        return notas;
    }

    public void setIdInm(String idInm) {
        this.idInm = idInm;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setNumPartida(String numPartida) {
        this.numPartida = numPartida;
    }

    public void setNumTGI(String numTGI) {
        this.numTGI = numTGI;
    }

    public void setNumTOS(String numTOS) {
        this.numTOS = numTOS;
    }

    public void setTitulares(String titulares) {
        this.titulares = titulares;
    }

    public void setNumRegPropiedad(String numRegPropiedad) {
        this.numRegPropiedad = numRegPropiedad;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setFechaRegProp(String fechaRegProp) {
        this.fechaRegProp = fechaRegProp;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Inmueble{" + "idInm=" + idInm + ", domicilio=" + domicilio + ", numPartida=" + numPartida + ", numTGI=" + numTGI + ", numTOS=" + numTOS + ", titulares=" + titulares + ", numRegPropiedad=" + numRegPropiedad + ", tomo=" + tomo + ", folio=" + folio + ", fechaRegProp=" + fechaRegProp + ", notas=" + notas + '}';
    }

   
    
    
    
    
}
