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
public class Seguro {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
     private String idSeguro;
  private String numeroCuenta;
  private String poliza;
  private String fechaVencSeguro;
  private int cuota;
  private Double importe;

    public Seguro() {
    }

    public Seguro(String numeroCuenta, String poliza, String fechaVencSeguro, int cuota, Double importe) {
        this.numeroCuenta = numeroCuenta;
        this.poliza = poliza;
        this.fechaVencSeguro = fechaVencSeguro;
        this.cuota = cuota;
        this.importe = importe;
    }
    
    

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getPoliza() {
        return poliza;
    }

    public String getFechaVencSeguro() {
        return fechaVencSeguro;
    }

    public int getCuota() {
        return cuota;
    }

    public Double getImporte() {
        return importe;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public void setFechaVencSeguro(String fechaVencSeguro) {
        this.fechaVencSeguro = fechaVencSeguro;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "Seguro{" + "numeroCuenta=" + numeroCuenta + ", poliza=" + poliza + ", fechaVencSeguro=" + fechaVencSeguro + ", cuota=" + cuota + ", importe=" + importe + '}';
    }
  
  
}
