/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Domicilio;
import com.blasdsaidon.fincasdellitoral.entidades.Inquilino;
import com.blasdsaidon.fincasdellitoral.repositorios.InquilinoRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author blasd
 */
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
}
