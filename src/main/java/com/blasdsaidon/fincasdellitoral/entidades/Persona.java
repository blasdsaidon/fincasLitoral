/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
 @MappedSuperclass
public abstract class Persona {
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idPersona;
    private String nombre;
    private String apellido;
    @OneToOne(cascade={CascadeType.REMOVE})
    private Domicilio domicilio;
    private String fechaNac;
    @Column(unique = true)
    private String dni;
    private String cuit;
    private String email;
    private String telefono;

    public Persona() {
    }

    public Persona(String idPersona, String nombre, String apellido, Domicilio domicilio, String fechaNac, String dni, String cuit, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaNac = fechaNac;
        this.dni = dni;
        this.cuit = cuit;
        this.email = email;
        this.telefono = telefono;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public String getCuit() {
        return cuit;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", domicilio=" + domicilio + ", fechaNac=" + fechaNac + ", dni=" + dni + ", cuit=" + cuit + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
    

 }
