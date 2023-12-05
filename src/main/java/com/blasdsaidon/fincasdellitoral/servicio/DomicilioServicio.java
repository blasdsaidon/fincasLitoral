/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Domicilio;
import com.blasdsaidon.fincasdellitoral.repositorios.DomicilioRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *private String calle;
    private String numero;
    private String piso;
    private String departamento;
    private String provincia;
    private String localidad;
 * 
 */
@Service
public class DomicilioServicio {
    
    @Autowired 
    private DomicilioRepositorio domicilioRepo;
    
    @Transactional
    public Domicilio crearDomicilio(String calle, String numero, String piso, String departamento, 
            String provincia, String localidad ){
        
        Domicilio domicilio = new Domicilio();
        
        domicilio.setCalle(calle);
        domicilio.setNumero(numero);
        domicilio.setPiso(piso);
        domicilio.setDepartamento(departamento);
        domicilio.setProvincia(provincia);
        domicilio.setLocalidad(localidad);
        
        Domicilio domicilioCreado = domicilioRepo.save(domicilio);
        
        
        return domicilioCreado;
        
    
}
    
    
    
    
}
