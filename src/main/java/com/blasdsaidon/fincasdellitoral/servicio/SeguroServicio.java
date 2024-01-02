/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Seguro;
import com.blasdsaidon.fincasdellitoral.repositorios.SeguroRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * private String numeroCuenta;
  private String poliza;
  private String fechaVencSeguro;
  private int cuota;
  private Long importe;

 * @author blasd
 */




@Service
public class SeguroServicio {
    
    @Autowired
    private SeguroRepositorio seguroRepo;
    
    @Transactional
    public Seguro crearSeguro(String numeroCuenta, String poliza, String fechaVencimiento){
        
        Seguro seguro = new Seguro();
        
        seguro.setFechaVencSeguro(fechaVencimiento);
        
        seguro.setPoliza(poliza);
        seguro.setNumeroCuenta(numeroCuenta);
        
        seguroRepo.save(seguro);
        
        return seguro;
    }
}
