/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Otros;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.repositorios.PagoRepositorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Period;
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

    



        // Crear una lista para almacenar las fechas como String
        List<String> listaFechasString = new ArrayList<>();

        // Agregar la fecha inicial a la lista
        listaFechasString.add(formatter.format(fechaInicial));

        
        

        // Agregar fechas aumentando de a mes
        for (int i = 0; i <= numMeses; i++) {
            LocalDate nuevaFecha = fechaInicial.plusMonths(i);
            
            Pago pago = new Pago();
            pago.setMesAno(formatter.format(nuevaFecha));
            pago.setRealizado(Boolean.FALSE);
            pago.setOtros(otroServicio.crearOtros());
            pago.setNumeroCuota(i+1);
            pagoRepo.save(pago);
            listaPago.add(pago);
        }

        
        return listaPago;
        
    }
    
    
    @Transactional
    public void guardarMontos(Double monto, Double montoAgua, Double montoTasa, String idPago,List<Otros> otros, Double interesesPuni, Double descuentoHono){
        
        
        
        Optional<Pago> respuesta = pagoRepo.findById(idPago);
        if(respuesta.isPresent()){
            Pago pago = respuesta.get();
            pago.setImporte(monto);
            pago.setImporteAgua(montoAgua);
            pago.setImporteTasa(montoTasa);
            pago.setOtros(otros);
            pago.setDescuentoHono(descuentoHono);
            pago.setInteresesPuni(interesesPuni);
            
            pagoRepo.save(pago);
        }
    }
    
    
}
