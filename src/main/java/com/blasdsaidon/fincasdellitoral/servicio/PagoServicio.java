/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Contrato;
import com.blasdsaidon.fincasdellitoral.entidades.ContratoHonorariosDTO;
import com.blasdsaidon.fincasdellitoral.entidades.Otros;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.repositorios.ContratoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.PagoRepositorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Period;
import java.util.Collection;
import java.util.Optional;

/**
 *import java.time.Period;

@Transactional
public List<Pago> crearPagos(String fechaInicio, String fechaFinal){
    List<Pago> listaPago = new ArrayList<>();
    
    // Formato de fecha
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Convertir las fechas a LocalDate
    LocalDate fechaInicial = LocalDate.parse(fechaInicio, formatter);
    LocalDate fechaFin = LocalDate.parse(fechaFinal, formatter);

    // Calcular la diferencia en meses entre las dos fechas
    Period periodo = Period.between(fechaInicial, fechaFin);
    int numMeses = periodo.getYears() * 12 + periodo.getMonths();

    // Agregar fechas aumentando de a mes
    for (int i = 0; i <= numMeses; i++) {
        LocalDate nuevaFecha = fechaInicial.plusMonths(i);
        
        Pago pago = new Pago();
        pago.setMesAno(formatter.format(nuevaFecha));
        pago.setRealizado(Boolean.FALSE);
        
        pago.setNumeroCuota(i+1);
        pagoRepo.save(pago);
        listaPago.add(pago);
    }

    return listaPago;
}

 * @author blasd
 */
@Service
public class PagoServicio {
    
    @Autowired
    private PagoRepositorio pagoRepo;
    @Autowired 
    private OtrosServicio otroServicio;
    @Autowired
    private ContratoRepositorio contratoRepo;
    @Transactional
public List<Pago> crearPagos(String fechaInicio, String fechaFinal) throws Exception {
    
        esDiferenciaMayorAUnMes(fechaInicio, fechaFinal);
    List<Pago> listaPago = new ArrayList<>();

    // Formato de fecha
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Convertir las fechas a LocalDate
    LocalDate fechaInicial = LocalDate.parse(fechaInicio, formatter);
    LocalDate fechaFin = LocalDate.parse(fechaFinal, formatter);

    // Calcular la diferencia en meses entre las dos fechas
    Period periodo = Period.between(fechaInicial, fechaFin);
    int numMeses = periodo.getYears() * 12 + periodo.getMonths();
    LocalDate fechaActual = LocalDate.now();
    // Agregar fechas aumentando de a mes
    for (int i = 0; i <= numMeses; i++) {
        // Establecer el día al número 10 de cada mes
        LocalDate nuevaFecha = LocalDate.of(fechaInicial.plusMonths(i).getYear(), fechaInicial.plusMonths(i).getMonth(), 10);

        Pago pago = new Pago();
        pago.setMesAno(formatter.format(nuevaFecha));
        if(nuevaFecha.isBefore(fechaActual)){
            pago.setRealizado(Boolean.TRUE);
        }else{
            pago.setRealizado(Boolean.FALSE);
        }
        pago.setOtros(otroServicio.crearOtros());
        pago.setNumeroCuota(i + 1);
        pagoRepo.save(pago);
        listaPago.add(pago);
    }

    return listaPago;
}


public void esDiferenciaMayorAUnMes(String fechaInicio, String fechaFin) throws Exception {
        

    
// Formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convertir las fechas a LocalDate
        LocalDate fechaInicial = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFinal = LocalDate.parse(fechaFin, formatter);

        // Calcular la diferencia en meses entre las dos fechas
        Period periodo = Period.between(fechaInicial, fechaFinal);
        
        if(periodo.toTotalMonths()<=1){
            throw new Exception("Contrato no creado, la diferencia entre inicio y fin es muy corta");
        }
        
        // Verificar si la diferencia en meses es mayor a 1
        
    }
    
    
    @Transactional
    public void guardarMontos(Double monto, Double montoAgua, Double montoTasa, String idPago,List<Otros> otros, Double interesesPuni, Double descuentoHono, String tipo, String idContrato){
        
        Contrato contrato = contratoRepo.getOne(idContrato);
        
        
        
        Optional<Pago> respuesta = pagoRepo.findById(idPago);
        if(respuesta.isPresent()){
            Pago pago = respuesta.get();
           if(tipo.equalsIgnoreCase("honorario")){
            Collection<Pago> locaciones =  contrato.getLocaciones();
               for (Pago locacione : locaciones) {
                   if(locacione.getNumeroCuota()==pago.getNumeroCuota()){
                       locacione.setImporte(monto);
                       pagoRepo.save(locacione);
                   }
               }

        }else if(tipo.equalsIgnoreCase("locacion")){
            Collection<Pago> honorarios =  contrato.getHonorarios();
               for (Pago honorario : honorarios) {
                   if(honorario.getNumeroCuota()==pago.getNumeroCuota()){
                       honorario.setImporte(monto);
                       pagoRepo.save(honorario);
                   }
               }

        }
            pago.setImporte(monto);
            pago.setImporteAgua(montoAgua);
            pago.setImporteTasa(montoTasa);
            pago.setOtros(otros);
            pago.setDescuentoHono(descuentoHono);
            pago.setInteresesPuni(interesesPuni);
            
            System.out.println("pago antes de save"+pago.toString());
            
            pagoRepo.save(pago);
        }
    }
    
    @Transactional
    public Pago getOne(String idPago){
        
        Pago pago = null;
        Optional<Pago> respuesta = pagoRepo.findById(idPago);
        
        if (respuesta.isPresent()) {
            
          pago = respuesta.get();
            
        }
        return pago;
    }
    
    @Transactional 
    public void guardaPago(Pago pago){
        
        pagoRepo.save(pago);
        
    }
    
   /* public List<ContratoHonorariosDTO> honorariosCobrados(){
        
        List<ContratoHonorariosDTO> cobrados = pagoRepo.obtenerDatosContratoHonorarios();
        
        return cobrados;
        
        
    }*/
    
    
}
