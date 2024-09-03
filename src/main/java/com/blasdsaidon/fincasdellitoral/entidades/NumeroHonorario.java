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
public class NumeroHonorario {
    
    private int numeroHono;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    public NumeroHonorario() {
    }

    public NumeroHonorario(int numeroHono, String id) {
        this.numeroHono = numeroHono;
        this.id = id;
    }

    

    public int getNumeroHono() {
        return numeroHono;
    }

    public void setNumeroHono(int numeroHono) {
        this.numeroHono = numeroHono;
    }

    @Override
    public String toString() {
        return "NumeroHonorario{" + "numeroHono=" + numeroHono + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
