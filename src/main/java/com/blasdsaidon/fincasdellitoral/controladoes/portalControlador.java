/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.controladoes;

import com.blasdsaidon.fincasdellitoral.entidades.Usuario;
import com.blasdsaidon.fincasdellitoral.repositorios.UsuarioRepositorio;
import com.blasdsaidon.fincasdellitoral.servicio.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author blasd
 */

@Controller
@RequestMapping("/")
public class portalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
     @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @GetMapping("/")
    public String landing(HttpSession session) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        
                
        if (logueado == null) {
           return "redirect:/login";
       }
        
        return "index.html";
    }
    
   // @GetMapping("/login")
    //public String login(@RequestParam(required = false) String error, ModelMap modelo, HttpSession session) {

     //   Usuario logueado = (Usuario) session.getAttribute("usuariosession");
      //  if (logueado != null) {
       //     return "redirect:/";
       // }
       // if (error != null) {
        //    modelo.put("error", "Usuario o Contraseña invalidos.");

       // }
       // return "login.html";
    //}
    
     @GetMapping("/login")
    public String mostrarFormularioLogin() {
        
        return "login";
    }
    
     
    
    @GetMapping("/registrar")
    public String registrar() {

        return "registro.html";
    }

    @PostMapping("/registroUser")
    public String registroUser(@RequestParam String nombre, @RequestParam String password,
            String password2, ModelMap modelo, RedirectAttributes redireccion) {

        try {
            usuarioServicio.registrar(nombre, password, password2);
            modelo.put("exito", "El usuario se registró exitosamente!");
            redireccion.addAttribute("exito", "El usuario se registró exitosamente!");
            return "redirect:/";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            return "registro.html";
        }
    }
    
    @GetMapping("/modificarPerfil/{idUsuario}")
    public String modificiarPerfil(@PathVariable String idUsuario, ModelMap modelo) {
        modelo.put("usuario", usuarioRepositorio.getOne(idUsuario));
        return "validar_contraseña.html";
    }
    
    @PostMapping("/validarContraseña/{idUsuario}")
    public String validarContraseña(@PathVariable String idUsuario, String password, ModelMap modelo){
         modelo.put("usuario", usuarioRepositorio.getOne(idUsuario));
         System.out.println(idUsuario + password);
        try {
           usuarioServicio.validarContraseña(idUsuario, password);
           return "modificar_perfil.html"; 
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            return "validar_contraseña.html"; 
        }
    }
    
       @PostMapping("/modificacionPerfil/{idUsuario}")
    public String modificacionPerfil(@RequestParam String nombreUsuario, @RequestParam String password,
            String password2, @PathVariable String idUsuario, ModelMap modelo, RedirectAttributes redireccion) {
           try {
            usuarioServicio.modificar(nombreUsuario, password, password2, idUsuario);
            modelo.put("exito", "El usuario se modifico exitosamente!");
            redireccion.addAttribute("exito", "El usuario se modifico exitosamente!");
            return "redirect:/";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            redireccion.addAttribute("nombreUsuario", nombreUsuario);
            redireccion.addAttribute("error", ex.getMessage());
            redireccion.addFlashAttribute("error", ex.getMessage());
            return "redirect:/modificarPerfil/" + idUsuario;
            
        }
    }

}
