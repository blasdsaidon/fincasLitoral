/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.List;
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
public class Contrato {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idContrato;
    @OneToOne
    private Inquilino inquilino;
    @OneToOne
    private Inmueble inmueble;
    @OneToMany
    private List<Codeudor> codeudores;
    @OneToMany
    private List<Archivo> archivos;
    private String fechaInicio;
    private String fechaFinaliz;
    private boolean fechaActualiza;
    @OneToMany
    private List<Pago> honorarios;
    @OneToMany
    private List<Pago> locaciones;
    @OneToOne
    private Seguro seguro;

    public Contrato() {
    }

    public Contrato(String idContrato, Inquilino inquilino, Inmueble inmueble, List<Codeudor> codeudores, List<Archivo> archivos, String fechaInicio, String fechaFinaliz, boolean fechaActualiza, List<Pago> honorarios, List<Pago> locaciones, Seguro seguro) {
        this.idContrato = idContrato;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.codeudores = codeudores;
        this.archivos = archivos;
        this.fechaInicio = fechaInicio;
        this.fechaFinaliz = fechaFinaliz;
        this.fechaActualiza = fechaActualiza;
        this.honorarios = honorarios;
        this.locaciones = locaciones;
        this.seguro = seguro;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public List<Codeudor> getCodeudores() {
        return codeudores;
    }

    public List<Archivo> getArchivos() {
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

    public List<Pago> getHonorarios() {
        return honorarios;
    }

    public List<Pago> getLocaciones() {
        return locaciones;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public void setCodeudores(List<Codeudor> codeudores) {
        this.codeudores = codeudores;
    }

    public void setArchivos(List<Archivo> archivos) {
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

    public void setHonorarios(List<Pago> honorarios) {
        this.honorarios = honorarios;
    }

    public void setLocaciones(List<Pago> locaciones) {
        this.locaciones = locaciones;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }


    
}
