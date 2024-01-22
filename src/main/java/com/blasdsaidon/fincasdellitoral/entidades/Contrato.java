/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
@Entity
public class Contrato {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idContrato;
    @OneToOne
    private Inquilino inquilino;
    @OneToOne
    private Propietario propietario;
    @OneToOne
    private Inmueble inmueble;
    @ManyToMany 
    private Collection<Codeudor> codeudores;
    @OneToMany(cascade={CascadeType.REMOVE})  
    private Collection<Archivo> archivos   ;
    private String fechaInicio;
    private String fechaFinaliz;
    private boolean esComercial;
    private String periodoActualiza;
    private Double porcentajeHonorario;

    
    
    private String indice;
    @OneToMany(cascade={CascadeType.REMOVE})  
    private Collection<Pago> honorarios ;
    @OneToMany(cascade={CascadeType.REMOVE})  
    private Collection<Pago> locaciones  ;
    @OneToOne(cascade={CascadeType.REMOVE})
    private Seguro seguro;
    
    private Integer numContrato;

    public Contrato() {
    }

    public Contrato(String idContrato, Inquilino inquilino, Propietario propietario, Inmueble inmueble, Collection<Codeudor> codeudores, Collection<Archivo> archivos, String fechaInicio, String fechaFinaliz, boolean esComercial, String periodoActualiza, Double porcentajeHonorario, String indice, Collection<Pago> honorarios, Collection<Pago> locaciones, Seguro seguro, Integer numContrato) {
        this.idContrato = idContrato;
        this.inquilino = inquilino;
        this.propietario = propietario;
        this.inmueble = inmueble;
        this.codeudores = codeudores;
        this.archivos = archivos;
        this.fechaInicio = fechaInicio;
        this.fechaFinaliz = fechaFinaliz;
        this.esComercial = esComercial;
        this.periodoActualiza = periodoActualiza;
        this.porcentajeHonorario = porcentajeHonorario;
        this.indice = indice;
        this.honorarios = honorarios;
        this.locaciones = locaciones;
        this.seguro = seguro;
        this.numContrato = numContrato;
    }

    public void setPorcentajeHonorario(Double porcentajeHonorario) {
        this.porcentajeHonorario = porcentajeHonorario;
    }

    public Double getPorcentajeHonorario() {
        return porcentajeHonorario;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public Collection<Codeudor> getCodeudores() {
        return codeudores;
    }

    public Collection<Archivo> getArchivos() {
        return archivos;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFinaliz() {
        return fechaFinaliz;
    }

    public boolean isEsComercial() {
        return esComercial;
    }

    public String getPeriodoActualiza() {
        return periodoActualiza;
    }

    public String getIndice() {
        return indice;
    }

    public Collection<Pago> getHonorarios() {
        return honorarios;
    }

    public Collection<Pago> getLocaciones() {
        return locaciones;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public Integer getNumContrato() {
        return numContrato;
    }

    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public void setCodeudores(Collection<Codeudor> codeudores) {
        this.codeudores = codeudores;
    }

    public void setArchivos(Collection<Archivo> archivos) {
        this.archivos = archivos;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFinaliz(String fechaFinaliz) {
        this.fechaFinaliz = fechaFinaliz;
    }

    public void setEsComercial(boolean esComercial) {
        this.esComercial = esComercial;
    }

    public void setPeriodoActualiza(String periodoActualiza) {
        this.periodoActualiza = periodoActualiza;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public void setHonorarios(Collection<Pago> honorarios) {
        this.honorarios = honorarios;
    }

    public void setLocaciones(Collection<Pago> locaciones) {
        this.locaciones = locaciones;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public void setNumContrato(Integer numContrato) {
        this.numContrato = numContrato;
    }

    
}