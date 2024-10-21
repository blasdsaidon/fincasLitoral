/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Archivo;
import com.blasdsaidon.fincasdellitoral.entidades.Codeudor;
import com.blasdsaidon.fincasdellitoral.entidades.Contrato;
import com.blasdsaidon.fincasdellitoral.entidades.Inmueble;
import com.blasdsaidon.fincasdellitoral.entidades.Inquilino;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.entidades.Propietario;
import com.blasdsaidon.fincasdellitoral.entidades.Seguro;
import com.blasdsaidon.fincasdellitoral.repositorios.ArchivoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.CodeudorRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.ContratoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.InmuebleRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.InquilinoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.PagoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.PropietarioRepositorio;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *  @OneToOne
    private Inquilino inquilino;
    @OneToOne
    private Propietario propietario;
    @OneToOne
    private Inmueble inmueble;
    @ManyToMany 
    private Collection<Codeudor> codeudores;
    @OneToMany  
    private Collection<Archivo> archivos   ;
    private String fechaInicio;
    private String fechaFinaliz;
    private boolean esComercial;
    private Integer periodoActualiza;
    
    private String indice;
    @OneToMany  
    private Collection<Pago> honorarios ;
    @OneToMany  
    private Collection<Pago> locaciones  ;
    @OneToOne
    private Seguro seguro;
    
    private Integer numContrato;
 */
@Service
public class ContratoServicio {
    
    @Autowired
    private ContratoRepositorio contratoRepositorio;
    @Autowired
    private PropietarioRepositorio propietarioRepo;
    @Autowired
    private InquilinoRepositorio inquilinoRepo;
    @Autowired
    private InmuebleRepositorio inmuebleRepo;
    @Autowired
    private CodeudorRepositorio codeudorRepo;
    @Autowired
    private ArchivoServicio archivoServicio;
    @Autowired
    private PagoServicio pagoServicio;
    @Autowired 
    private PagoRepositorio pagoRepo;
    @Autowired
    private SeguroServicio seguroServicio;
    @Autowired
    private InquilinoServicio inquilinoServicio;
    @Autowired
    private PropietarioServicio propietarioServicio;
    @Autowired 
    private CodeudorServicio codeudorServicio;
    
    
    @Transactional
    public void crearContrato (String esComercial, String periodoActualiza, String indice, String fechaInicio, ArrayList<String> codeudores, String fechaFin, String idInq, String idProp, String idInm, List<MultipartFile> archivos,Integer numContrato, String numeroCuenta, String poliza, String fechaVenceSeguro, Double porcentajeHono) throws Exception{
        
        if(fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            
           throw new Exception("Contrato no creado, ninguna fecha seleccionada");
        }
        
        
        if(codeudores==null){
            throw new Exception("Contrato no creado, ningun codeudor seleccionado");
        }else if(codeudores.isEmpty()){
            throw new Exception("Contrato no creado, ningun codeudor seleccionado");
        }
        Seguro seguro = seguroServicio.crearSeguro(numeroCuenta, poliza, fechaVenceSeguro);
        //modificar el array de codeudores recibidos
          List<String> codeudoress = codeudores.stream()
                .map(s -> s.replaceAll("[\\[\\]\"]", ""))
                .collect(Collectors.toList());
          
       /*List<Pago> honorarios, List<Pago> locaciones,*/
       List<Pago> honorarios=pagoServicio.crearPagos(fechaInicio, fechaFin);
       List<Pago> locaciones=pagoServicio.crearPagos(fechaInicio, fechaFin);
       
        
        
        
        Propietario propietario = new Propietario();
        
        Inmueble inmueble =  new Inmueble();
       
        Inquilino inquilino = new Inquilino();
       
        boolean comercial=false;
        Optional<Propietario> respuestaProp = propietarioRepo.findById(idProp);
        if (respuestaProp.isPresent()) {
            propietario = respuestaProp.get();
        }
        Optional<Inmueble> respuestaInm = inmuebleRepo.findById(idInm);
        if (respuestaInm.isPresent()) {
            inmueble = respuestaInm.get();
        }
        Optional<Inquilino> respuestaInq = inquilinoRepo.findById(idInq);
        if (respuestaInq.isPresent()) {
            inquilino = respuestaInq.get();
        }
        
        if (esComercial.equalsIgnoreCase("comercial")) {
             comercial=true;
            
        
             
            
        }
            
        Contrato contrato = new Contrato();
        Collection<Codeudor> codeudoresNueva = new ArrayList<>();
     
        for (String cadaCodeudor : codeudoress) {
                
                Optional<Codeudor> respuestaCod = codeudorRepo.findById(cadaCodeudor);
              
                if (respuestaCod.isPresent()) {
               
                  
              Codeudor codeudor = respuestaCod.get();
        
            
            codeudoresNueva.add(codeudor);
            
        }
            }
        
       
        
        
        Collection<Archivo> archivosNueva = archivoServicio.guardar(archivos);
        
       
        
        contrato.setInquilino(inquilino);
        contrato.setPorcentajeHonorario(porcentajeHono);
        contrato.setInmueble(inmueble);
        contrato.setPropietario(propietario);
        contrato.setCodeudores(codeudoresNueva);
        contrato.setArchivos(archivosNueva);
        contrato.setHonorarios(honorarios);
        contrato.setLocaciones(locaciones);
        contrato.setSeguro(seguro);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFinaliz(fechaFin);
        contrato.setEsComercial(comercial);
        contrato.setNumContrato(numContrato);
        contrato.setIndice(indice);
        contrato.setPeriodoActualiza(periodoActualiza);
        
        
       
        contratoRepositorio.save(contrato);
            
    
}
    
    @Transactional
    public void agregarArchivo(String idContrato, MultipartFile documento) throws Exception{
        
        Archivo archivo = archivoServicio.guardarUno(documento);
        
        Contrato contrato = getOne(idContrato);
        
        Collection<Archivo> archivos = contrato.getArchivos();
        
        archivos.add(archivo);
     
        contrato.setArchivos(archivos);
        
        contratoRepositorio.save(contrato);
        
               
    }
    
    
    @Transactional
    public List<Contrato> mostraContrato(){
        List<Contrato> contratoLista = contratoRepositorio.findAllByOrderByNumContratoAsc();
        
        return contratoLista;
    }
    
    @Transactional
    public Contrato getOne(String idContrato){
        Contrato contrato = new Contrato();
        Optional<Contrato> respuesta = contratoRepositorio.findById(idContrato);
        if (respuesta.isPresent()) {
            contrato = respuesta.get();
            List<Pago> listaordenadaHon = pagoRepo.findSortedByMesAno(contrato.getHonorarios());
            List<Pago> listaordenadaLoc = pagoRepo.findSortedByMesAno(contrato.getLocaciones());
            contrato.setLocaciones(listaordenadaLoc);
            contrato.setHonorarios(listaordenadaHon);
        }
        return contrato;
        
        
    }
    
 /*   @Transactional
    public Pago[] ultimasCuotas(String idContrato, String tipoPago){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaActual = LocalDate.now();
        Pago ultimaCuota = new Pago();
        
        Contrato contrato = getOne(idContrato);
        List<Pago> lista = null;
        Pago actualYSiguiente[] = new Pago[2];
        
        if (tipoPago.equalsIgnoreCase("locacion")) {
            lista = (List<Pago>) contrato.getLocaciones();
        }else{
            lista = (List<Pago>) contrato.getHonorarios();
        }
        
          for (Pago cuota : lista) {
            LocalDate cuotaFecha = LocalDate.parse(cuota.getMesAno(), formatter);

            // Si la cuota es después de la última cuota conocida y antes o igual a la fecha actual
            if (cuotaFecha.isAfter(fechaActual)) {
                ultimaCuota = cuota;
                
                actualYSiguiente[0]=cuota;
                
                if(lista.get(lista.indexOf(cuota)+1) != null){
                actualYSiguiente[1]=lista.get(lista.indexOf(cuota)+1);
                
                }
                
                break;
            }
            
        }
            
            
            
        return actualYSiguiente;    
        
    }*/
    
    @Transactional
    public Pago[] ultimasCuotas(String idContrato, String tipoPago){
       
        Contrato contrato = getOne(idContrato);
        List<Pago> lista = null;
        Pago actualYSiguiente[] = new Pago[2];
        
        if (tipoPago.equalsIgnoreCase("locacion")) {
            lista = (List<Pago>) contrato.getLocaciones();
        }else{
            lista = (List<Pago>) contrato.getHonorarios();
        }
        Collections.sort(lista, (pago1, pago2) -> Integer.compare(pago1.getNumeroCuota(), pago2.getNumeroCuota()));
       
        for (Pago pago : lista) {
            if(!pago.getRealizado()){
                
                actualYSiguiente[0]=pago;
                
                if( lista.indexOf(pago)<lista.size()-1 && lista.get(lista.indexOf(pago)+1) != null){
                actualYSiguiente[1]=lista.get(lista.indexOf(pago)+1);
                
                }
                break;
            }
            
        }
       
        return actualYSiguiente;  
    }
    
    public void montoSeguro(String idContrato, Double seguroImporte){
        Optional<Contrato> respuesta = contratoRepositorio.findById(idContrato);
        
        if (respuesta.isPresent()) {
            Contrato contrato =  respuesta.get();
            
            contrato.getSeguro().setImporte(seguroImporte);
            
            contratoRepositorio.save(contrato);
        }
        
    }
    
    @Transactional
    public void modificarContrato(String idContrato, String indice, String periodoActualiza, String tipoContrato, 
    String numeroCuenta, String poliza, String fechaVencSeguro, Double porcentajeHono  ){
    
        Contrato contrato = getOne(idContrato);
        
        seguroServicio.modificarSeguro(contrato.getSeguro().getIdSeguro(), numeroCuenta, poliza, fechaVencSeguro);
        
        contrato.setIndice(indice);
        contrato.setPeriodoActualiza(periodoActualiza);
        contrato.setEsComercial(false);
        contrato.setPorcentajeHonorario(porcentajeHono);
        
        if (tipoContrato.equalsIgnoreCase("comercial")) {
            contrato.setEsComercial(true);
        }
    }
        @Transactional    
    public void eliminarArchivo(String idContrato, String idArchivo){
        
            
        
        Contrato contrato = getOne(idContrato);
         
        Collection<Archivo> archivos = contrato.getArchivos();
        
            Archivo archivo = archivoServicio.getOne(idArchivo);
         
            archivos.remove(archivo);
           
            contrato.setArchivos(archivos);
            
            contratoRepositorio.save(contrato);
           
            archivoServicio.borrar(idArchivo);
            
        
     
    
    }

    @Transactional
    public void eliminarContrato(String idContrato){
        
        Contrato contrato=getOne(idContrato);
        
        contrato.setCodeudores(null);
        contrato.setInmueble(null);
        contrato.setPropietario(null);
        contrato.setInquilino(null);
        
        contratoRepositorio.delete(contrato);
        
        
    }
    
    public List<Contrato> obtenerContratosPorCodeudorId(String idPersona) {
        return contratoRepositorio.findContratosByCodeudorId(idPersona);
    }
    
    @Transactional
    public void eliminarCodeudoresContrato(String idPersona) {
        List<Contrato> contratosCodeudores = obtenerContratosPorCodeudorId(idPersona);
        
        for (Contrato contrato : contratosCodeudores) {
            List <Codeudor> codeudores = (List <Codeudor>) contrato.getCodeudores();
            Optional <Codeudor> respuesta = codeudorRepo.findById(idPersona);
            if (respuesta.isPresent()) {
               Codeudor codeudor = respuesta.get();
               codeudores.remove(codeudor);
            }
            
            contrato.setCodeudores(codeudores);
        }
        
    }
    
    public List<Contrato> obtenerContratoPorInquilinoId(String idPersona) {
        return contratoRepositorio.findContratosByInquilinoId(idPersona);
    }
    
    @Transactional
    public void eliminarInquilinoContrato(String idPersona) {
        List<Contrato> contratosInquilino = obtenerContratoPorInquilinoId(idPersona);
        
        for (Contrato contrato : contratosInquilino) {
            
            contrato.setInquilino(null);
            contratoRepositorio.save(contrato);
            
        }
        
    }
    
    public List<Contrato> obtenerContratoPorPropietarioId(String idPersona) {
        return contratoRepositorio.findContratosByPropietarioId(idPersona);
    }
    
    @Transactional
    public void eliminarPropietarioContrato(String idPersona) {
        List<Contrato> contratosPropietarios = obtenerContratoPorPropietarioId(idPersona);
        
        for (Contrato contrato : contratosPropietarios) {
            
            contrato.setPropietario(null);
            contratoRepositorio.save(contrato);
            
        }
        
    }
    
    @Transactional
    public void agregarPersonaContrato(String tipoPersona, String idPersona, String idContrato){
        if(tipoPersona.equals("locatario")){
            
            Inquilino inquilino = inquilinoServicio.getOne(idPersona);
            
            Contrato contrato = getOne(idContrato);
           
            contrato.setInquilino(inquilino);
           
            contratoRepositorio.save(contrato);
        }
        if(tipoPersona.equals("locador")){
            Propietario propietario = propietarioServicio.getOne(idPersona);
            Contrato contrato = getOne(idContrato);
            contrato.setPropietario(propietario);
            contratoRepositorio.save(contrato);
        }
        if(tipoPersona.equals("codeudor")){
            Codeudor codeudor = codeudorServicio.getOne(idPersona);
            Contrato contrato = getOne(idContrato);
            List <Codeudor> codeudores = (List <Codeudor>) contrato.getCodeudores();
            if (!codeudores.contains(codeudor)) {
    codeudores.add(codeudor);
    contrato.setCodeudores(codeudores);
    contratoRepositorio.save(contrato);
} else {
    // Opcional: manejar el caso en el que el codeudor ya está en la lista
    
}
        }
        
    }
    
    @Transactional
    public void contratoInactivo(String idContrato){
        
        Contrato contrato = getOne(idContrato);
        
        contrato.setInactivo(true);
        
        contratoRepositorio.save(contrato);
    
}
          
}
