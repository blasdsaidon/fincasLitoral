/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

/**
 *
 * @author blasd
 */
public class ContratoHonorariosDTO {

   
    private String mesAno;
    private int numeroCuota;
    private String numContrato;
    private Double descuentoHono;

    public ContratoHonorariosDTO(String mesAno, int numeroCuota, String numContrato, Double descuentoHono) {
        this.mesAno = mesAno;
        this.numeroCuota = numeroCuota;
        this.numContrato = numContrato;
        this.descuentoHono = descuentoHono;
    }

    public ContratoHonorariosDTO() {
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }

    public Double getDescuentoHono() {
        return descuentoHono;
    }

    public void setDescuentoHono(Double descuentoHono) {
        this.descuentoHono = descuentoHono;
    }

    @Override
    public String toString() {
        return "ContratoHonorariosDTO{" + "mesAno=" + mesAno + ", numeroCuota=" + numeroCuota + ", numContrato=" + numContrato + ", descuentoHono=" + descuentoHono + '}';
    }
    
    
}
