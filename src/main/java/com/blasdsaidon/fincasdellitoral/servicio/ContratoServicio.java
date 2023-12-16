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
import com.blasdsaidon.fincasdellitoral.repositorios.PropietarioRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *  @OneToOne
    private Inquilino inquilino;
    @OneToOne
    private Inmueble inmueble;
    @OneToMany
    private List<Codeudor> codeudores;
    @OneToMany
    private List<Archivo> archivos;
    private String fechaInicio;
    private String fechaFinaliz;
    private boolean fechaActualiza;
    @OneToMany
    private List<Pago> honorarios;
    @OneToMany
    private List<Pago> locaciones;
    @OneToOne
    private Seguro seguro;
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
    
    
    
    @Transactional
    public void crearContrato (String fechaActualiza, /* Seguro seguro,*/ String fechaInicio, ArrayList<String> codeudores, String fechaFin, String idInq, String idProp, String idInm, List<MultipartFile> archivos) throws Exception{
       
        //modificar el array de codeudores recibidos
          List<String> codeudoress = codeudores.stream()
                .map(s -> s.replaceAll("[\\[\\]\"]", ""))
                .toList();
       /*List<Pago> honorarios, List<Pago> locaciones,*/
       List<Pago> honorarios=pagoServicio.crearPagos(fechaInicio);
       List<Pago> locaciones=pagoServicio.crearPagos(fechaInicio);
       
        
        
        System.out.println("in service");
        Propietario propietario = new Propietario();
        System.out.println("in service despues propietario");
        Inmueble inmueble =  new Inmueble();
        System.out.println("in service despues inmueble");
        Inquilino inquilino = new Inquilino();
        System.out.println("in service despues inquilino");
        boolean fechaActualizar=false;
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
        
        if (fechaActualiza.equalsIgnoreCase("nuevo")) {
             fechaActualizar=true;
            
        
             
            
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
        contrato.setInmueble(inmueble);
        contrato.setPropietario(propietario);
        contrato.setCodeudores(codeudoresNueva);
        contrato.setArchivos(archivosNueva);
        contrato.setHonorarios(honorarios);
        contrato.setLocaciones(locaciones);
        //contrato.setSeguro(seguro);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFinaliz(fechaFin);
        contrato.setFechaActualiza(fechaActualizar);
        
        
        System.out.println("*********************" + contrato.toString());
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
        }
        return contrato;
    }
}
