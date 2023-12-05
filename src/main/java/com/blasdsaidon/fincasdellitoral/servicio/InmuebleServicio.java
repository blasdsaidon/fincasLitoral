/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Domicilio;
import com.blasdsaidon.fincasdellitoral.entidades.Inmueble;
import com.blasdsaidon.fincasdellitoral.entidades.Propietario;
import com.blasdsaidon.fincasdellitoral.repositorios.InmuebleRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.PropietarioRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
    @OneToOne
    private Domicilio domicilio; 
    private String numPartida;
    private String numTGI;
    private String numTOS;
    @OneToOne
    private Propietario propietario;
    private String numRegPropiedad;
    private String tomo;
    private String folio;
    private String fechaRegProp;
    private String notas;
 * 
 */


@Service
public class InmuebleServicio {
    
    @Autowired
    private InmuebleRepositorio inmuebleRepo;
    
    @Autowired
    private PropietarioRepositorio propietarioRepo;
    
    @Autowired
    private DomicilioServicio domicilioServicio;
    
    @Transactional
    public void crearInmueble(String calle, String numero, String piso, String departamento, 
            String provincia, String localidad, String numPartida, String numTGI, String numTOS,
            String idProp, String numRegPropiedad, String tomo,String folio, String fechaRegProp,
            String notas){
        
        Inmueble inmueble= new Inmueble();
        
        Domicilio domicilio = domicilioServicio.crearDomicilio(calle, numero, piso, departamento, provincia, localidad );
        
        Optional<Propietario> respuesta = propietarioRepo.findById(idProp);
            
        if(respuesta.isPresent()){
            Propietario propietario = respuesta.get();
            
            inmueble.setDomicilio(domicilio);
            inmueble.setPropietario(propietario);
            inmueble.setFechaRegProp(fechaRegProp);
            inmueble.setFolio(folio);
            inmueble.setNotas(notas);
            inmueble.setNumPartida(numPartida);
            inmueble.setNumTGI(numTGI);
            inmueble.setNumTOS(numTOS);
            inmueble.setNumRegPropiedad(numRegPropiedad);
            inmueble.setTomo(tomo);
            
            inmuebleRepo.save(inmueble);        }
        
    }
}
