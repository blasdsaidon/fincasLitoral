/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.controladores;

import com.blasdsaidon.fincasdellitoral.servicio.PropietarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @Controller
@RequestMapping("/propietario")
public class PropietarioControlador {
    
    
    @Autowired
    private PropietarioServicio propietarioServicio;
    
    @PostMapping("/propietario/crear")
    public String crearProp( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, @RequestParam(required=false)  String piso,  @RequestParam(required=false)String departamento, 
            String provincia, String localidad ){
        try {
            propietarioServicio.crearPropietario(nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return "redirect:/";
    }
}
 */

