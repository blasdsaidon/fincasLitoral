/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
    @Entity
public class Archivo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id; 
    private String extensión;
    private String mime;
    
    private String nombre;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;

    public Archivo() {
    }

    public Archivo(String id, String extensión, String mime, String nombre, byte[] contenido) {
        this.id = id;
        this.extensión = extensión;
        this.mime = mime;
        this.nombre = nombre;
        this.contenido = contenido;
    }

    public String getExtensión() {
        return extensión;
    }

    public void setExtensión(String extensión) {
        this.extensión = extensión;
    }

   

    public String getId() {
        return id;
    }

    public String getMime() {
        return mime;
    }

    public String getNombre() {
        return nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    
    
}
