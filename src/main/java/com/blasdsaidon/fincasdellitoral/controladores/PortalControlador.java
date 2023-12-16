/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.controladores;

import com.blasdsaidon.fincasdellitoral.entidades.Archivo;
import com.blasdsaidon.fincasdellitoral.entidades.Codeudor;
import com.blasdsaidon.fincasdellitoral.entidades.Contrato;
import com.blasdsaidon.fincasdellitoral.entidades.Inmueble;
import com.blasdsaidon.fincasdellitoral.entidades.Inquilino;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.entidades.Propietario;
import com.blasdsaidon.fincasdellitoral.entidades.Seguro;
import com.blasdsaidon.fincasdellitoral.entidades.Usuario;
import com.blasdsaidon.fincasdellitoral.repositorios.ArchivoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.UsuarioRepositorio;
import com.blasdsaidon.fincasdellitoral.servicio.CodeudorServicio;
import com.blasdsaidon.fincasdellitoral.servicio.ContratoServicio;
import com.blasdsaidon.fincasdellitoral.servicio.PropietarioServicio;
import com.blasdsaidon.fincasdellitoral.servicio.UsuarioServicio;
import com.blasdsaidon.fincasdellitoral.servicio.InmuebleServicio;
import com.blasdsaidon.fincasdellitoral.servicio.InquilinoServicio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author blasd
 */

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
     @Autowired
    private UsuarioRepositorio usuarioRepositorio;
     @Autowired
     private PropietarioServicio propietarioServicio;
     
     @Autowired
     private InmuebleServicio inmuebleServicio;
     
     @Autowired
     private CodeudorServicio codeudorServicio;
     
     @Autowired
     private InquilinoServicio inquilinoServicio;
     
     @Autowired
     private ContratoServicio contratoServicio;
     @Autowired
     private ArchivoRepositorio archivoRepo;
     
    /*
     @GetMapping("/")
    public String index(ModelMap modelo){
        List<Proveedor> listaProve = proveServicio.mostraProve();
        
        modelo.addAttribute("listaPropietario", listaPropietario);
    return "index";
     */
     
    @GetMapping("/")
    public String landing(HttpSession session, ModelMap modelo) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        List<Propietario> listaPropietario = propietarioServicio.mostraPropietario();
        List<Inquilino> listaInquilino = inquilinoServicio.mostraInquilino();
        List<Codeudor> listaCodeudor = codeudorServicio.mostraCodeudor();
        List<Inmueble> listaInmueble = inmuebleServicio.mostraInmueble();
         modelo.addAttribute("listaPropietario", listaPropietario);  
         modelo.addAttribute("listaInquilino", listaInquilino);   
         modelo.addAttribute("listaCodeudor", listaCodeudor);   
         modelo.addAttribute("listaInmueble", listaInmueble);   
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
    
    @PostMapping("/inmueble/crear")
    public String crearInmueble (String calle, String numero, @RequestParam(required=false) String piso, @RequestParam(required=false) String departamento, 
            String provincia, String localidad, String numPartida, String numTGI, String numTOS,
            String titulares, String numRegPropiedad, String tomo,String folio, String fechaRegProp,
            @RequestParam(required=false) String notas){
        
        try {
            inmuebleServicio.crearInmueble(calle, numero, piso, departamento, provincia, localidad, numPartida, numTGI, numTOS, titulares, numRegPropiedad, tomo, folio, fechaRegProp, notas);
        } catch (Exception e) {
             e.printStackTrace();
        }
        
    
        return "redirect:/";
    }
    
    @PostMapping("/codeudor/crear")
    public String crearCodeudor ( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, @RequestParam(required=false)  String piso,  @RequestParam(required=false)String departamento, 
            String provincia, String localidad ){
        
        try {
            codeudorServicio.crearCodeudor(nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
        @PostMapping("/inquilino/crear")
        public String crearInquilino ( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, @RequestParam(required=false)  String piso,  @RequestParam(required=false)String departamento, 
            String provincia, String localidad ){
                
                try {
            inquilinoServicio.crearInquilino(nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
                return "redirect:/";
            }
        
        @PostMapping("/contrato/crear")
        public String crearContrato (String fechaActualiza, /*@RequestParam(required=false)List<Pago> honorarios,@RequestParam(required=false) List<Pago> locaciones, @RequestParam(required=false)Seguro seguro,*/ String fechaInicio,@RequestParam ArrayList<String> codeudores, String fechaFin, String idInq, String idProp, String idInm, List<MultipartFile> archivos){
            try {
                System.out.println("entro a controlador");
                contratoServicio.crearContrato(fechaActualiza, /*honorarios, locaciones, seguro,*/ fechaInicio, codeudores, fechaFin, idInq, idProp, idInm, archivos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return "redirect:/";
        }
        
        @GetMapping("/contratos")
        public String listado_contratos (ModelMap modelo, HttpSession session ){
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            List<Contrato> contratos = contratoServicio.mostraContrato();
             modelo.addAttribute("listaContratos", contratos);  
            
            return "lista_contratos.html";
        }
        
        @GetMapping("/contratos/{idContrato}")
        public String detalleContrato(@PathVariable String idContrato, ModelMap modelo, HttpSession session){
            Contrato contrato = contratoServicio.getOne(idContrato);
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            modelo.addAttribute("contrato", contrato);
            
            return "contrato_detalle.html";
        }
        
        @GetMapping("/archivo/{idArchivo}")
        public ResponseEntity<byte[]> mostrarArchivo(@PathVariable String idArchivo){
            
   
        Archivo archivo = archivoRepo.getOne(idArchivo);

        byte[] archivoContenido = archivo.getContenido();

        HttpHeaders headers = new HttpHeaders();
    
    // Aquí puedes detectar el tipo de archivo y establecer el Content-Type en consecuencia
        if (archivo.getMime().startsWith("image/")) {
        headers.setContentType(MediaType.IMAGE_JPEG);
        } else if (archivo.getMime().equals("application/pdf")) {
        headers.setContentType(MediaType.APPLICATION_PDF);
        //}else if (archivo.getMime().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
            //headers.setContentType(MediaType.);
        
        } else {
        // Tipo predeterminado si no se detecta un tipo específico
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }   
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(archivo.getNombre()).build());

       
        
        return new ResponseEntity<>(archivoContenido, headers, HttpStatus.OK);
}
}
        
    


