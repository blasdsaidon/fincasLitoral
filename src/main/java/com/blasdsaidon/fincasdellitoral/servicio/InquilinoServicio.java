/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Domicilio;
import com.blasdsaidon.fincasdellitoral.entidades.Inquilino;
import com.blasdsaidon.fincasdellitoral.repositorios.InquilinoRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author blasd
 */
@Service
public class InquilinoServicio {
    
    @Autowired
    private InquilinoRepositorio inquilinoRepo;
    
     @Autowired
    private DomicilioServicio domicilioServicio;
    
    @Transactional
    public void crearInquilino( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, String piso, String departamento, 
            String provincia, String localidad ){
        
        Domicilio domicilio = domicilioServicio.crearDomicilio(calle, numero, piso, departamento, provincia, localidad);
        
        Inquilino inquilino = new Inquilino();
        
        inquilino.setNombre(nombre);
        inquilino.setApellido(apellido);
        inquilino.setDni(dni);
        inquilino.setCuit(cuit);
        inquilino.setEmail(email);
        inquilino.setFechaNac(fechaNac);
        inquilino.setTelefono(telefono);
        inquilino.setDomicilio(domicilio);
        
        inquilinoRepo.save(inquilino);
   
    }
    
    @Transactional
    public Inquilino getOne(String idInquilino){
        
        Inquilino inquilino = null;
        
        Optional<Inquilino> respuesta = inquilinoRepo.findById(idInquilino);
        
        if (respuesta.isPresent()) {
            inquilino = respuesta.get();
        }
        
        return inquilino;
        
    }
    
    @Transactional
    public List<Inquilino> mostraInquilino(){
        List<Inquilino> inquilinoLista = inquilinoRepo.findAll();
        
        return inquilinoLista;
    }
    
    @Transactional 
    public void modificarInquilino(String idPersona, String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, String piso, String departamento, 
            String provincia, String localidad){
        
        Optional<Inquilino> respuesta = inquilinoRepo.findById(idPersona);
        
        if (respuesta.isPresent()) {
            
            Inquilino inquilino = respuesta.get();
            
            Domicilio domicilio = domicilioServicio.modificarDomicilio(inquilino.getDomicilio().getIdDom(), 
            calle,  numero,  piso,  departamento, provincia,  localidad);
            
            inquilino.setNombre(nombre);
            inquilino.setApellido(apellido);
            inquilino.setTelefono(telefono);
            inquilino.setDni(dni);
            inquilino.setCuit(cuit);
            inquilino.setEmail(email);
            inquilino.setFechaNac(fechaNac);
            inquilino.setDomicilio(domicilio);
            
            inquilinoRepo.save(inquilino);
            
        }
        
    }
}
