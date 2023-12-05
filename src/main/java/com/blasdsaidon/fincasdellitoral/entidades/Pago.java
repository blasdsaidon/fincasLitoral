/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    public Pago() {
    }

    public String getMesAno() {
        return mesAno;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public Pago(String mesAno, Boolean realizado) {
        this.mesAno = mesAno;
        this.realizado = realizado;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    @Override
    public String toString() {
        return "Pago{" + "mesAno=" + mesAno + ", realizado=" + realizado + '}';
    }
    
    
    
}
