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
public class Otros {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idOtros;
    private String concepto;
    private Double monto;

    public String getIdOtros() {
        return idOtros;
    }

    public void setIdOtros(String idOtros) {
        this.idOtros = idOtros;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Otros() {
    }

    public Otros(String idOtros, String concepto, Double monto) {
        this.idOtros = idOtros;
        this.concepto = concepto;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Otros{" + "idOtros=" + idOtros + ", concepto=" + concepto + ", monto=" + monto + '}';
    }


}
