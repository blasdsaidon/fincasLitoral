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
import com.blasdsaidon.fincasdellitoral.entidades.Otros;
import com.blasdsaidon.fincasdellitoral.entidades.ContratoHonorariosDTO;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.entidades.Persona;
import com.blasdsaidon.fincasdellitoral.entidades.Propietario;
import com.blasdsaidon.fincasdellitoral.entidades.Seguro;
import com.blasdsaidon.fincasdellitoral.entidades.Usuario;
import com.blasdsaidon.fincasdellitoral.repositorios.ArchivoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.PagoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.ReciboRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.UsuarioRepositorio;
import com.blasdsaidon.fincasdellitoral.servicio.ArchivoServicio;
import com.blasdsaidon.fincasdellitoral.servicio.CodeudorServicio;
import com.blasdsaidon.fincasdellitoral.servicio.ContratoServicio;
import com.blasdsaidon.fincasdellitoral.servicio.PropietarioServicio;
import com.blasdsaidon.fincasdellitoral.servicio.UsuarioServicio;
import com.blasdsaidon.fincasdellitoral.servicio.InmuebleServicio;
import com.blasdsaidon.fincasdellitoral.servicio.InquilinoServicio;
import com.blasdsaidon.fincasdellitoral.servicio.OtrosServicio;
import com.blasdsaidon.fincasdellitoral.servicio.PagoServicio;
import com.blasdsaidon.fincasdellitoral.servicio.ReciboServicio;
import excepciones.CustomNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author blasd
 */

@Controller
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    private ReciboRepositorio reciboRepo;
    @Autowired
    private PagoRepositorio pagoRepo;
    @Autowired
    private ArchivoServicio archivoServicio;
    @Autowired
    private ReciboServicio reciboServicio;
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
     
     @Autowired
     private PagoServicio pagoServicio;
     
     @Autowired
     private OtrosServicio otrosServicio;
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
        
        
        return "redirect:/#locatario";
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
        
    
        return "redirect:/#inmueble";
    }
    
    @GetMapping("/inmueble/{idInmueble}/{idContrato}")
    public String detalleInmueble(@PathVariable String idInmueble,@PathVariable String idContrato, ModelMap modelo){
        
        Inmueble inmueble = inmuebleServicio.getOne(idInmueble);
        
        modelo.addAttribute("inmueble", inmueble);
        modelo.addAttribute("idContrato", idContrato);
        
        return "inmueble_detalle.html";
    }
    
    @PostMapping("/modificar/{idInmueble}")
    public String modificarInmueble(String idContrato , @PathVariable String idInmueble, String calle, String numero, String piso, String departamento, 
            String provincia, String localidad, String partida, String numTGI, String numTOS,
            String titulares, String numRegPropiedad, String tomo,String folio, String fechaRegProp,
            String notas){
        
        inmuebleServicio.modificarInmueble(idInmueble, calle, numero, piso, departamento, provincia, localidad, partida, numTGI, numTOS, titulares, numRegPropiedad, tomo, folio, fechaRegProp, notas);
        
        return "redirect:/contratos/"+idContrato;
    }
    
    @PostMapping("/codeudor/crear")
    public String crearCodeudor ( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, @RequestParam(required=false)  String piso,  @RequestParam(required=false)String departamento, 
            String provincia, String localidad ){
        
        try {
            codeudorServicio.crearCodeudor(nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/#codeudor";
    }
        @PostMapping("/inquilino/crear")
        public String crearInquilino ( String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, @RequestParam(required=false)  String piso,  @RequestParam(required=false)String departamento, 
            String provincia, String localidad ){
                
                try {
            inquilinoServicio.crearInquilino(nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
        } catch (Exception e) {
            e.printStackTrace();
        }
                return "redirect:/#locador";
            }
        
        @PostMapping("/modificar/{idPersona}/{tipoPersona}")
        public String modificarPersona(@PathVariable String idPersona,@PathVariable String tipoPersona, String idContrato, String nombre, String apellido, String fechaNac, String dni, String cuit, String email, String telefono,   String calle,   String numero, String piso, String departamento, 
            String provincia, String localidad ){
            System.out.println("aca id contrato");
            System.out.println(idContrato);
            if (tipoPersona.equalsIgnoreCase("locador")) {
            inquilinoServicio.modificarInquilino(idPersona, nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
            }
            
            if (tipoPersona.equalsIgnoreCase("locatario")) {
                propietarioServicio.modificarPropietario(idPersona, nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
            }
            if (tipoPersona.equalsIgnoreCase("codeudor")) {
                codeudorServicio.modificarCodeudor(idPersona, nombre, apellido, fechaNac, dni, cuit, email, telefono, calle, numero, piso, departamento, provincia, localidad);
            }
            
            if (tipoPersona!=null && idContrato.isEmpty()) {
                return "redirect:/listaPersonas/"+ tipoPersona;
           
            }else {
                 return "redirect:/contratos/"+idContrato;
            }
        }
        
        @GetMapping("/persona/{idPersona}/{tipoPersona}")
        public String detallePersonas(@PathVariable String tipoPersona, @PathVariable String idPersona, ModelMap modelo ){
           
            if (tipoPersona.equalsIgnoreCase("locador")) {
                Inquilino inquilino = inquilinoServicio.getOne(idPersona);
                 modelo.addAttribute("persona", inquilino);
            }
            if (tipoPersona.equalsIgnoreCase("Codeudor")) {
                Codeudor codeudor = codeudorServicio.getOne(idPersona);
                modelo.addAttribute("persona", codeudor);
            }
            
            if (tipoPersona.equalsIgnoreCase("locatario")) {
                Propietario propietario = propietarioServicio.getOne(idPersona);
                 modelo.addAttribute("persona", propietario);
            }
            
            
            modelo.addAttribute("tipoPersona", tipoPersona);
            return "persona_detalle";
        }
        
        @GetMapping("/persona/contrato/{idPersona}/{tipoPersona}/{idContrato}")
        public String detallePersona(@PathVariable String idContrato, @PathVariable String tipoPersona, @PathVariable String idPersona, ModelMap modelo ){
           
            if (tipoPersona.equalsIgnoreCase("locador")) {
                Inquilino inquilino = inquilinoServicio.getOne(idPersona);
                 modelo.addAttribute("persona", inquilino);
            }
            if (tipoPersona.equalsIgnoreCase("codeudor")) {
                Codeudor codeudor = codeudorServicio.getOne(idPersona);
                modelo.addAttribute("persona", codeudor);
            }
            
            if (tipoPersona.equalsIgnoreCase("locatario")) {
                Propietario propietario = propietarioServicio.getOne(idPersona);
                 modelo.addAttribute("persona", propietario);
            }
            
            modelo.addAttribute("idContrato", idContrato);
            modelo.addAttribute("tipoPersona", tipoPersona);
            return "persona_detalle";
        }
        
        /*(String esComercial, Integer periodoActualiza, String indice, String fechaInicio, ArrayList<String> codeudores, String fechaFin, String idInq, String idProp, String idInm, List<MultipartFile> archivos,Integer numContrato, String numeroCuenta, String poliza, String fechaVenceSeguro)*/
        @PostMapping("/contrato/crear")
        public String crearContrato (String esComercial, String periodoActualiza, String indice, String poliza, String numeroCuenta, String fechaVenceSeguro, String fechaInicio,@RequestParam(name = "codeudores", required = false) ArrayList<String> codeudores, String fechaFin, String idInq, String idProp, String idInm, List<MultipartFile> archivos, Integer numContrato,RedirectAttributes redirectAttributes, Double porcentajeHono){
            try {
                
                contratoServicio.crearContrato(esComercial, periodoActualiza, indice, fechaInicio, codeudores, fechaFin, idInq, idProp, idInm, archivos, numContrato, numeroCuenta, poliza, fechaVenceSeguro, porcentajeHono);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/#contrato";
                
            }
            
            return "redirect:/#contrato";
        }
        
        @DeleteMapping("/archivo/eliminar/{idArchivo}/{idContrato}")
    public ResponseEntity<String> eliminarArchivo(@PathVariable String idArchivo, @PathVariable String idContrato ) {
        contratoServicio.eliminarArchivo(idContrato, idArchivo);
        return ResponseEntity.ok("Archivo eliminado correctamente");
    }
    
    @PostMapping("/archivo/agregar/{idContrato}")
    public String agregarArchivo(@PathVariable String idContrato, MultipartFile archivo) throws Exception{
        
        System.out.println("archivo" + archivo.getOriginalFilename());
        
        contratoServicio.agregarArchivo(idContrato, archivo);
        
        
        
        return "redirect:/contratos/"+idContrato;
    }
        
        @GetMapping("/contrato/eliminar/{idContrato}")
        public String eliminarContrato(@PathVariable String idContrato){
            contratoServicio.eliminarContrato(idContrato);
            
            return "redirect:/";
        }
        
        
        @GetMapping("/contrato/{idContrato}")
        public String modificarContrato (@PathVariable String idContrato, ModelMap modelo){
            
            Contrato contrato = contratoServicio.getOne(idContrato);
            modelo.addAttribute("contrato",contrato);
            
            return "modifica_contrato.html";
            
        }
        
        @PostMapping("/modificarContrato/{idContrato}")
        public String modificaContrato(@PathVariable String idContrato, String indice, String periodoActualiza, String tipoContrato, 
    String numeroCuenta, String poliza, String fechaVencSeguro, Double porcentajeHono ){
            
            contratoServicio.modificarContrato(idContrato, indice, periodoActualiza, tipoContrato, numeroCuenta, poliza, fechaVencSeguro, porcentajeHono);
            
            return "redirect:/contratos/"+idContrato;
        }
        
        @GetMapping("/listaPersonas/{tipoPersona}")
        public String listado_personas (ModelMap modelo, HttpSession session, @PathVariable String tipoPersona ){
            modelo.addAttribute(tipoPersona, tipoPersona);
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            if(tipoPersona.equals("codeudor")){
                List<Codeudor> codeudores = codeudorServicio.mostraCodeudor();
                modelo.addAttribute("listaPersonas", codeudores);
                modelo.addAttribute("titulo", "Lista de Codeudores");
            }
            if(tipoPersona.equals("locador")){
                List<Inquilino> locadores = inquilinoServicio.mostraInquilino();
                modelo.addAttribute("listaPersonas", locadores);
                modelo.addAttribute("titulo", "Lista de Locadores");
            }
            if(tipoPersona.equals("locatario")){
                List<Propietario> propietarios = propietarioServicio.mostraPropietario();
                modelo.addAttribute("listaPersonas", propietarios);
                modelo.addAttribute("titulo", "Lista de Locatarios");
            }
             
             if (logueado == null) {
           return "redirect:/login";
       }
            
            return "lista_personas.html";
        }
        
        @GetMapping("/contratos")
        public String listado_contratos (ModelMap modelo, HttpSession session ){
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            List<Contrato> contratos = contratoServicio.mostraContrato();
             modelo.addAttribute("listaContratos", contratos);  
             if (logueado == null) {
           return "redirect:/login";
       }
            
            return "lista_contratos.html";
        }
        
        @GetMapping("/contratos/{idContrato}")
        public String detalleContrato(@PathVariable String idContrato, ModelMap modelo, HttpSession session){
            Pago[] ultimosHonos = contratoServicio.ultimasCuotas(idContrato, "honorario");
            
            Pago[] ultimasLocas = contratoServicio.ultimasCuotas(idContrato, "locacion");
            Pago ultimoHono=ultimosHonos[0];
            
            if (ultimoHono!=null && ultimoHono.getOtros()!=null) {
                
            
            ArrayList<Otros> ordenOtrosHono = new ArrayList<>(ultimoHono.getOtros());
            Collections.sort(ordenOtrosHono, Comparator
    .comparing(Otros::getMonto, Comparator.nullsLast(Comparator.naturalOrder()))
    .thenComparing(Otros::getConcepto, Comparator.nullsLast(Comparator.naturalOrder())));
            
            ordenOtrosHono.sort(
            Comparator.comparingInt(otros ->
                Objects.requireNonNullElse(otros.getOrden(), Integer.MAX_VALUE)
            )
        );
                    
            ultimoHono.setOtros(ordenOtrosHono);
            }
            
            Pago siguienteHono=ultimosHonos[1];
            Pago ultimaLoca=ultimasLocas[0];
            
            if(ultimaLoca!=null && ultimaLoca.getOtros()!=null){
            ArrayList<Otros> ordenOtrosLoca = new ArrayList<>(ultimaLoca.getOtros());
            Collections.sort(ordenOtrosLoca, Comparator
            .comparing(Otros::getMonto, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(Otros::getConcepto, Comparator.nullsLast(Comparator.naturalOrder())));
           
            ordenOtrosLoca.sort(
            Comparator.comparingInt(otros ->
                Objects.requireNonNullElse(otros.getOrden(), Integer.MAX_VALUE)
            )
        );
                    
            ultimaLoca.setOtros(ordenOtrosLoca);
            }
            Pago siguienteLoca=ultimasLocas[1];
            Contrato contrato = contratoServicio.getOne(idContrato);
            
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            modelo.addAttribute("contrato", contrato);
            modelo.addAttribute("ultimoHono", ultimoHono);
            modelo.addAttribute("ultimaLoca", ultimaLoca);
            modelo.addAttribute("siguienteHono", siguienteHono);
            modelo.addAttribute("siguienteLoca", siguienteLoca);
            if (logueado == null) {
           return "redirect:/login";
       }
            
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
        
        @GetMapping("/recibo/{idContrato}/{idPago}")
        public String recibo(@PathVariable String idContrato, @PathVariable String idPago, ModelMap modelo){
            System.out.println("idPago" + idPago);
            Optional<Pago> respuesta = pagoRepo.findById(idPago);
            Pago pago=new Pago();
            if (respuesta.isPresent()) {
                pago = respuesta.get();
                
            }
            if(pago.getOtros()!=null){
            ArrayList<Otros> ordenOtrosPago = new ArrayList<>(pago.getOtros());
            Collections.sort(ordenOtrosPago, Comparator
            .comparing(Otros::getMonto, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(Otros::getConcepto, Comparator.nullsLast(Comparator.naturalOrder())));
           
              ordenOtrosPago.sort(
            Comparator.comparingInt(otros ->
                Objects.requireNonNullElse(otros.getOrden(), Integer.MAX_VALUE)
            )
        );
            
            pago.setOtros(ordenOtrosPago);
            }
            Contrato contrato = contratoServicio.getOne(idContrato);
            int numeroHonorario = 0;
            if(!reciboRepo.findAll().isEmpty()){
            numeroHonorario = reciboRepo.encontrarMaxIdentificadorHonorario();
            }
            numeroHonorario = numeroHonorario + 1;
            System.out.println("numerphonorario " + numeroHonorario);    
            String stringHonorario=String.format("%07d", numeroHonorario);
            System.out.println("string honorario"+stringHonorario);
            modelo.addAttribute("contrato", contrato);
            modelo.addAttribute("pago", pago);
            modelo.addAttribute("stringHonorario", stringHonorario);
            
            
            return "recibo.html";
        }
        
        @GetMapping("/recibo_loca/{idContrato}/{idPago}")
        public String reciboLoca(@PathVariable String idContrato, @PathVariable String idPago, ModelMap modelo){
            System.out.println("idPago" + idPago);
            Optional<Pago> respuesta = pagoRepo.findById(idPago);
            Pago pago=new Pago();
            if (respuesta.isPresent()) {
                pago = respuesta.get();
                
            }
            if(pago.getOtros()!=null){
            ArrayList<Otros> ordenOtrosPago = new ArrayList<>(pago.getOtros());
            Collections.sort(ordenOtrosPago, Comparator
            .comparing(Otros::getMonto, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(Otros::getConcepto, Comparator.nullsLast(Comparator.naturalOrder())));
            
              ordenOtrosPago.sort(
            Comparator.comparingInt(otros ->
                Objects.requireNonNullElse(otros.getOrden(), Integer.MAX_VALUE)
            )
        );
            
            pago.setOtros(ordenOtrosPago);
            }
            Contrato contrato = contratoServicio.getOne(idContrato);
            
            int numeroLocacion = 0;
            if(!reciboRepo.findAll().isEmpty()){
            numeroLocacion = reciboRepo.encontrarMaxIdentificadorLocacion();
            }
            numeroLocacion = numeroLocacion + 1;
            
            String stringLocacion = String.format("%07d", numeroLocacion);
            
            modelo.addAttribute("contrato", contrato);
            modelo.addAttribute("pago", pago);
            modelo.addAttribute("stringLocacion",stringLocacion);
            
            return "recibo_locaciones.html";
        }
        
        @PostMapping("/datosReciboHono/{idContrato}")
        public String cargarMontosHono(@PathVariable String idContrato, String idPago, String idPagoSgte,  Double monto, Double montoAgua, Double montoTasa, String concepto1, String concepto2, String concepto3, Double monto1, Double monto2, Double monto3, Double interesesPuni, Double descuentoHono,String tipo){
            
            ArrayList<Otros> otros = otrosServicio.insertarOtros(concepto1, concepto2, concepto3, monto1, monto2, monto3);
            ArrayList<Otros> otrosSgte = otrosServicio.insertarOtros(concepto1, concepto2, concepto3, monto1, monto2, monto3);
            
            System.out.println("descuendotot hono " + descuentoHono);
            pagoServicio.guardarMontos(monto, montoAgua, montoTasa, idPago, otros, interesesPuni, descuentoHono,tipo,idContrato);
            
            if(idPagoSgte!=null){
            pagoServicio.guardarMontos(monto, montoAgua, montoTasa, idPagoSgte, otrosSgte, interesesPuni, descuentoHono,tipo,idContrato);
            }
            return "redirect:/contratos/"+idContrato;
        }
        
         @PostMapping("/datosReciboLoca/{idContrato}")
        public String cargarMontosLoca(@PathVariable String idContrato, String idPagoLoca, String idPagoSgteLoca,  Double montoLoca, Double montoAguaLoca, Double montoTasaLoca ,Double seguroImporte,String concepto1, String concepto2, String concepto3, Double monto1, Double monto2, Double monto3,Double interesesPuni, Double descuentoHono, String tipo){
            ArrayList<Otros> otros = otrosServicio.insertarOtros(concepto1, concepto2, concepto3, monto1, monto2, monto3);
            ArrayList<Otros> otrosSgte = (ArrayList<Otros>) otrosServicio.crearOtros();
     System.out.println("antes de guardar");
          System.out.println("idContrato" + idContrato);
          System.out.println("idSgte "+ idPagoSgteLoca);
           System.out.println("idLoa "+ idPagoLoca);
            contratoServicio.montoSeguro(idContrato, seguroImporte);
            pagoServicio.guardarMontos(montoLoca, montoAguaLoca, montoTasaLoca, idPagoLoca, otros, interesesPuni, descuentoHono, tipo, idContrato );
            Double interesesSgte = 0.0;
            
            if(idPagoSgteLoca!=null){
            pagoServicio.guardarMontos(montoLoca, montoAguaLoca, montoTasaLoca, idPagoSgteLoca, otrosSgte, interesesSgte, descuentoHono,tipo,idContrato);
              }
            System.out.println("despues de guardar");
            return "redirect:/contratos/"+idContrato;
        }
        
        @PostMapping("/api/pdf/guardar")
    public ResponseEntity<String> guardarPdf(@RequestParam("file") MultipartFile file, @RequestParam("idCuota") String idCuota, @RequestParam("identificador") String identificador, @RequestParam("tipoRecibo") String tipoRecibo) throws Exception {
        
        reciboServicio.guardarRecibo(file, idCuota, identificador, tipoRecibo);

        return ResponseEntity.ok("PDF guardado exitosamente");
    }
    
    @GetMapping("/mostrar-datos")
    public String mostrarDatos(ModelMap model) {
        List<Object[]> datos = pagoRepo.obtenerDatosContratoHonorarios();
        model.addAttribute("datos", datos);
        return "mostrar_datos.html"; // Esto se corresponde con el nombre de tu plantilla Thymeleaf
    }
    
   @Controller
public class GlobalExceptionHandler implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        // Aquí puedes personalizar el mensaje de error genérico
        model.addAttribute("error1", "Ocurrió un error inesperado");
        return "error1";
    }

    public String getErrorPath() {
        return "/error1";
    }
}

    // Agrega más métodos @ExceptionHandler para otros tipos de excepciones según sea necesario

    
       @GetMapping("/sample")
    public String sample() {
        // Lanza una excepción para probar el manejo de errores
        throw new RuntimeException("Este es un error de ejemplo");
    }

}
         

    

    


