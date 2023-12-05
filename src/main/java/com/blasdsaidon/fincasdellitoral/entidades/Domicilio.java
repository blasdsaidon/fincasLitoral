/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
@Entity
public class Domicilio {
    
     @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idDom;
    private String calle;
    private String numero;
    private String piso;
    private String departamento;
    private String provincia;
    private String localidad;

    public Domicilio() {
    }

    public Domicilio(String idDom, String calle, String numero, String piso, String departamento, String provincia, String localidad) {
        this.idDom = idDom;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.departamento = departamento;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public String getIdDom() {
        return idDom;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getPiso() {
        return piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setIdDom(String idDom) {
        this.idDom = idDom;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Domicilio{" + "idDom=" + idDom + ", calle=" + calle + ", numero=" + numero + ", piso=" + piso + ", departamento=" + departamento + ", provincia=" + provincia + ", localidad=" + localidad + '}';
    }
    
    
    
}
