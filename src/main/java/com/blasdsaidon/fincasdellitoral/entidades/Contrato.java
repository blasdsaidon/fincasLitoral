/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
    @OneToMany  
    private Collection<Archivo> archivos   ;
    private String fechaInicio;
    private String fechaFinaliz;
    private boolean fechaActualiza;
    @OneToMany  
    private Collection<Pago> honorarios ;
    @OneToMany  
    private Collection<Pago> locaciones  ;
    @OneToOne
    private Seguro seguro;
    private Double importe;
    private Double importeAgua;
    private Double importeTasa;

    public Contrato() {
    }

    public Contrato(String idContrato, Inquilino inquilino, Propietario propietario, Inmueble inmueble, Collection<Codeudor> codeudores, Collection<Archivo> archivos, String fechaInicio, String fechaFinaliz, boolean fechaActualiza, Collection<Pago> honorarios, Collection<Pago> locaciones, Seguro seguro, Double importe, Double importeAgua, Double importeTasa) {
        this.idContrato = idContrato;
        this.inquilino = inquilino;
        this.propietario = propietario;
        this.inmueble = inmueble;
        this.codeudores = codeudores;
        this.archivos = archivos;
        this.fechaInicio = fechaInicio;
        this.fechaFinaliz = fechaFinaliz;
        this.fechaActualiza = fechaActualiza;
        this.honorarios = honorarios;
        this.locaciones = locaciones;
        this.seguro = seguro;
        this.importe = importe;
        this.importeAgua = importeAgua;
        this.importeTasa = importeTasa;
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

    public boolean isFechaActualiza() {
        return fechaActualiza;
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

    public Double getImporte() {
        return importe;
    }

    public Double getImporteAgua() {
        return importeAgua;
    }

    public Double getImporteTasa() {
        return importeTasa;
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

    public void setFechaActualiza(boolean fechaActualiza) {
        this.fechaActualiza = fechaActualiza;
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

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public void setImporteAgua(Double importeAgua) {
        this.importeAgua = importeAgua;
    }

    public void setImporteTasa(Double importeTasa) {
        this.importeTasa = importeTasa;
    }

    @Override
    public String toString() {
        return "Contrato{" + "idContrato=" + idContrato + ", inquilino=" + inquilino + ", propietario=" + propietario + ", inmueble=" + inmueble + ", codeudores=" + codeudores + ", archivos=" + archivos + ", fechaInicio=" + fechaInicio + ", fechaFinaliz=" + fechaFinaliz + ", fechaActualiza=" + fechaActualiza + ", honorarios=" + honorarios + ", locaciones=" + locaciones + ", seguro=" + seguro + ", importe=" + importe + ", importeAgua=" + importeAgua + ", importeTasa=" + importeTasa + '}';
    }
    
    

}