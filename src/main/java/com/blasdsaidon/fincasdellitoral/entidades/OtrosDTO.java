/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

/**
 *
 * @author blasd
 */
public class OtrosDTO {

   
    private String concepto;
    private Integer monto;

    public OtrosDTO() {
    }
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public OtrosDTO(String concepto, Integer monto) {
        this.concepto = concepto;
        this.monto = monto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    // getters, setters y constructores

   
}
