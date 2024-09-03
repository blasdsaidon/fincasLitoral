/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author blasd
 */
@Entity
public class Recibo {
    @Id
   @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idRecibo; 
    private int identificadorHonorario;
    private int identificadorLocacion;
    @OneToOne(cascade={CascadeType.REMOVE}) 
    private Archivo pdf;

    public Recibo() {
    }

    public Recibo(String idRecibo, int identificadorHonorario, int identificadorLocacion, Archivo pdf) {
        this.idRecibo = idRecibo;
        this.identificadorHonorario = identificadorHonorario;
        this.identificadorLocacion = identificadorLocacion;
        this.pdf = pdf;
    }

    

    public String getIdRecibo() {
        return idRecibo;
    }

    

    public Archivo getPdf() {
        return pdf;
    }

    public void setIdRecibo(String idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdentificadorHonorario() {
        return identificadorHonorario;
    }

    public void setIdentificadorHonorario(int identificadorHonorario) {
        this.identificadorHonorario = identificadorHonorario;
    }

    public int getIdentificadorLocacion() {
        return identificadorLocacion;
    }

    public void setIdentificadorLocacion(int identificadorLocacion) {
        this.identificadorLocacion = identificadorLocacion;
    }

    

    public void setPdf(Archivo pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "Recibo{" + "idRecibo=" + idRecibo + ", identificadorHonorario=" + identificadorHonorario + ", identificadorLocacion=" + identificadorLocacion + ", pdf=" + pdf + '}';
    }

    
    
    
    
}
