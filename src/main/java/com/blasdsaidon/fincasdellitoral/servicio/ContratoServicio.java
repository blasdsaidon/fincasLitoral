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
    
    
    
    @Transactional
    public void crearContrato (String esComercial, String periodoActualiza, String indice, String fechaInicio, ArrayList<String> codeudores, String fechaFin, String idInq, String idProp, String idInm, List<MultipartFile> archivos,Integer numContrato, String numeroCuenta, String poliza, String fechaVenceSeguro, Double porcentajeHono) throws Exception{
       
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
       
        
        
        System.out.println("in service");
        Propietario propietario = new Propietario();
        System.out.println("in service despues propietario");
        Inmueble inmueble =  new Inmueble();
        System.out.println("in service despues inmueble");
        Inquilino inquilino = new Inquilino();
        System.out.println("in service despues inquilino");
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
           System.out.println("codeudores"+codeudores.toString());  
        Contrato contrato = new Contrato();
        Collection<Codeudor> codeudoresNueva = new ArrayList<>();
     
        for (String cadaCodeudor : codeudoress) {
                System.out.println("entral al for"+cadaCodeudor);
                Optional<Codeudor> respuestaCod = codeudorRepo.findById(cadaCodeudor);
                System.out.println("hay algo en el optional"+respuestaCod);
                if (respuestaCod.isPresent()) {
               
                  
              Codeudor codeudor = respuestaCod.get();
              System.out.println("codeudor " + codeudor.toString());
            
            codeudoresNueva.add(codeudor);
            
        }
            }
        System.out.println("###############"+codeudoresNueva);
       
        
        
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
        System.out.println("antes de agregar el nuevo"+archivos);
        archivos.add(archivo);
        System.out.println("despues de agregar archivo nuevo"+archivos);
        contrato.setArchivos(archivos);
        
        contratoRepositorio.save(contrato);
        
               
    }
    
    
    @Transactional
    public List<Contrato> mostraContrato(){
        List<Contrato> contratoLista = contratoRepositorio.findAll();
        
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
        System.out.println("idContrato"+idContrato);
        if (tipoPago.equalsIgnoreCase("locacion")) {
            lista = (List<Pago>) contrato.getLocaciones();
        }else{
            lista = (List<Pago>) contrato.getHonorarios();
        }
        Collections.sort(lista, (pago1, pago2) -> Integer.compare(pago1.getNumeroCuota(), pago2.getNumeroCuota()));
        System.out.println("despues de ordenar -----------------------");
        for (Pago pago : lista) {
            if(!pago.getRealizado()){
                
                actualYSiguiente[0]=pago;
                
                if( lista.indexOf(pago)<lista.size()-1 && lista.get(lista.indexOf(pago)+1) != null){
                actualYSiguiente[1]=lista.get(lista.indexOf(pago)+1);
                
                }
                break;
            }
            
        }
        System.out.println("despues del for-------------");
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
    String numeroCuenta, String poliza, String fechaVencSeguro  ){
    
        Contrato contrato = getOne(idContrato);
        
        seguroServicio.modificarSeguro(contrato.getSeguro().getIdSeguro(), numeroCuenta, poliza, fechaVencSeguro);
        
        contrato.setIndice(indice);
        contrato.setPeriodoActualiza(periodoActualiza);
        contrato.setEsComercial(false);
        
        if (tipoContrato.equalsIgnoreCase("comercial")) {
            contrato.setEsComercial(true);
        }
    }
        @Transactional    
    public void eliminarArchivo(String idContrato, String idArchivo){
        
            System.out.println("idcontrato"+idContrato);
            System.out.println("idArchivo"+idArchivo);
        
        Contrato contrato = getOne(idContrato);
            System.out.println("coontrato"+contrato);
        Collection<Archivo> archivos = contrato.getArchivos();
        
            Archivo archivo = archivoServicio.getOne(idArchivo);
            System.out.println("++++++++++"+archivo.getId());
            archivos.remove(archivo);
            System.out.println("archivos"+archivos.size());
            contrato.setArchivos(archivos);
            
            contratoRepositorio.save(contrato);
            System.out.println("despues de contrato save");
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
    
}
