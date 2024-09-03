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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




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
            String titulares, String numRegPropiedad, String tomo,String folio, String fechaRegProp,
            String notas){
        
        Inmueble inmueble= new Inmueble();
        
        Domicilio domicilio = domicilioServicio.crearDomicilio(calle, numero, piso, departamento, provincia, localidad );
        
       
            
        
            
            inmueble.setDomicilio(domicilio);
            inmueble.setTitulares(titulares);
            inmueble.setFechaRegProp(fechaRegProp);
            inmueble.setFolio(folio);
            inmueble.setNotas(notas);
            inmueble.setNumPartida(numPartida);
            inmueble.setNumTGI(numTGI);
            inmueble.setNumTOS(numTOS);
            inmueble.setNumRegPropiedad(numRegPropiedad);
            inmueble.setTomo(tomo);
            
            inmuebleRepo.save(inmueble);        }
        
    
    
    @Transactional
    public List<Inmueble> mostraInmueble(){
        List<Inmueble> inmuebleLista = inmuebleRepo.findAll();
        
        return inmuebleLista;
    }
    
    @Transactional
    public Inmueble getOne(String idInmueble){
        Inmueble inmueble = null;
        Optional<Inmueble> respuesta = inmuebleRepo.findById(idInmueble);
        
        if (respuesta.isPresent()) {
            
            inmueble = respuesta.get();
            
        }
        
        return inmueble;
        
    }
    
    @Transactional
    public void modificarInmueble(String idInmueble, String calle, String numero, String piso, String departamento, 
            String provincia, String localidad, String numPartida, String numTGI, String numTOS,
            String titulares, String numRegPropiedad, String tomo,String folio, String fechaRegProp,
            String notas){
        
        Inmueble inmueble = getOne(idInmueble);
        
        Domicilio domicilio = domicilioServicio.modificarDomicilio(inmueble.getDomicilio().getIdDom(), 
                calle, numero, piso, departamento, provincia, localidad);
        
        inmueble.setDomicilio(domicilio);
        inmueble.setFechaRegProp(fechaRegProp);
        inmueble.setFolio(folio);
        inmueble.setNotas(notas);
        inmueble.setNumPartida(numPartida);
        inmueble.setNumRegPropiedad(numRegPropiedad);
        inmueble.setNumTGI(numTGI);
        inmueble.setNumTOS(numTOS);
        inmueble.setTitulares(titulares);
        inmueble.setTomo(tomo);
        
        
        inmuebleRepo.save(inmueble);
        
    }
}
