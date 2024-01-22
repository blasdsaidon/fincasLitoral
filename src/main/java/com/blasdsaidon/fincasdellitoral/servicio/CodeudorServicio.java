/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Domicilio;
import com.blasdsaidon.fincasdellitoral.entidades.Codeudor;
import com.blasdsaidon.fincasdellitoral.repositorios.CodeudorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * private String idPersona;
    private String nombre;
    private String apellido;
    @OneToOne
    private Domicilio domicilio;
    private String fechaNac;
    @Column(unique = true)
    private String dni;
    private String cuit;
    private String email;
    private String telefono;
 */
@Service
public class CodeudorServicio {
   
    
    @Autowired
    private CodeudorRepositorio codeudorRepo;
    
    @Autowired 
    private DomicilioServicio domicilioServicio;
    
    @Transactional
    public void crearCodeudor( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, String piso, String departamento, 
            String provincia, String localidad ){
        
        Domicilio domicilio = domicilioServicio.crearDomicilio(calle, numero, piso, departamento, provincia, localidad);
        
        Codeudor codeudor = new Codeudor();
        
        codeudor.setNombre(nombre);
        codeudor.setApellido(apellido);
        codeudor.setDni(dni);
        codeudor.setCuit(cuit);
        codeudor.setEmail(email);
        codeudor.setFechaNac(fechaNac);
        codeudor.setTelefono(telefono);
        codeudor.setDomicilio(domicilio);
        
        codeudorRepo.save(codeudor);
    }
    
    @Transactional 
    public void modificarCodeudor(String idPersona, String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, String piso, String departamento, 
            String provincia, String localidad){
        
        Optional<Codeudor> respuesta = codeudorRepo.findById(idPersona);
        
        if (respuesta.isPresent()) {
            
            Codeudor codeudor = respuesta.get();
            
            Domicilio domicilio = domicilioServicio.modificarDomicilio(codeudor.getDomicilio().getIdDom(), 
            calle,  numero,  piso,  departamento, provincia,  localidad);
            
            codeudor.setNombre(nombre);
            codeudor.setApellido(apellido);
            codeudor.setTelefono(telefono);
            codeudor.setDni(dni);
            codeudor.setCuit(cuit);
            codeudor.setEmail(email);
            codeudor.setFechaNac(fechaNac);
            codeudor.setDomicilio(domicilio);
            
            codeudorRepo.save(codeudor);
            
        }
        
    }
    
    @Transactional
    public List<Codeudor> mostraCodeudor(){
        List<Codeudor> codeudorLista = codeudorRepo.findAll();
        
        return codeudorLista;
    }
    
      @Transactional
    public Codeudor getOne(String idCodeudor){
        
        Codeudor codeudor = null;
        
        Optional<Codeudor> respuesta = codeudorRepo.findById(idCodeudor);
        
        if (respuesta.isPresent()) {
            codeudor = respuesta.get();
        }
        
        return codeudor;
        
    }
}
