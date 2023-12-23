/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.repositorios.PagoRepositorio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author blasd
 */
@Service
public class PagoServicio {
    
    @Autowired
    private PagoRepositorio pagoRepo;
    
    @Transactional
    public List<Pago> crearPagos(String fechaInicio){
        List<Pago> listaPago = new ArrayList<>();
        
        

        // Formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convertir la fecha inicial a LocalDate
        LocalDate fechaInicial = LocalDate.parse(fechaInicio, formatter);

        // Crear una lista para almacenar las fechas como String
        List<String> listaFechasString = new ArrayList<>();

        // Agregar la fecha inicial a la lista
        listaFechasString.add(formatter.format(fechaInicial));

        // Número de meses que deseas agregar
        int numMeses = 36;

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
    
    
    
}
