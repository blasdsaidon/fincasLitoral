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
public class NumeroLocacion {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private int numeroLoca;

    public NumeroLocacion() {
    }

    public NumeroLocacion(String id, int numeroLoca) {
        this.id = id;
        this.numeroLoca = numeroLoca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NumeroLocacion(int numeroLoca) {
        this.numeroLoca = numeroLoca;
    }

    public int getNumeroLoca() {
        return numeroLoca;
    }

    public void setNumeroLoca(int numeroLoca) {
        this.numeroLoca = numeroLoca;
    }

    @Override
    public String toString() {
        return "NumeroLocacion{" + "id=" + id + ", numeroLoca=" + numeroLoca + '}';
    }

  
    
    
}