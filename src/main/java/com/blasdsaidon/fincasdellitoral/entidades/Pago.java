/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
@Entity
public class Pago {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idPago;
    private String mesAno;
    private Boolean realizado;
    @OneToOne
    private Recibo recibo;
    private int numeroCuota;

    public Pago() {
    }

    public String getIdPago() {
        return idPago;
    }

    public String getMesAno() {
        return mesAno;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", mesAno=" + mesAno + ", realizado=" + realizado + ", recibo=" + recibo + ", numeroCuota=" + numeroCuota + '}';
    }

   
    
    
}
