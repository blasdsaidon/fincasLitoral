/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Domicilio;
import com.blasdsaidon.fincasdellitoral.entidades.Propietario;
import com.blasdsaidon.fincasdellitoral.repositorios.PropietarioRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *private String idPersona;
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
 * 
 */
@Service
public class PropietarioServicio {
    
    @Autowired
    private PropietarioRepositorio propietarioRepo;
    
    @Autowired
    private DomicilioServicio domicilioServicio;
    
    @Transactional
    public void crearPropietario( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, String piso, String departamento, 
            String provincia, String localidad ){
        
        Domicilio domicilio = domicilioServicio.crearDomicilio(calle, numero, piso, departamento, provincia, localidad);
        
        Propietario propietario = new Propietario();
        
        propietario.setNombre(nombre);
        propietario.setApellido(apellido);
        propietario.setDni(dni);
        propietario.setCuit(cuit);
        propietario.setEmail(email);
        propietario.setFechaNac(fechaNac);
        propietario.setTelefono(telefono);
        propietario.setDomicilio(domicilio);
        
        propietarioRepo.save(propietario);
   
    }
    
    @Transactional
    public List<Propietario> mostraPropietario(){
        List<Propietario> propietarioLista = propietarioRepo.findAll();
        
        return propietarioLista;
    }
    
    
}
