/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Archivo;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.entidades.Recibo;
import com.blasdsaidon.fincasdellitoral.repositorios.ReciboRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author blasd
 */
@Service
public class ReciboServicio {

    @Autowired
    private ReciboRepositorio reciboRepo;
    
    @Autowired
    private ArchivoServicio archivoServicio;
    @Autowired
    private PagoServicio pagoServicio;
    
    @Transactional
    public void guardarRecibo(MultipartFile pdf, String idPago, String identificador, String tipoRecibo) throws Exception{
        
        Archivo archivo = archivoServicio.guardarUno(pdf);
        
        Recibo recibo = new Recibo();
        int numeroSinCeros = Integer.parseInt(identificador);
      
        
        if(tipoRecibo.equalsIgnoreCase("honorario")){
            recibo.setIdentificadorHonorario(numeroSinCeros);
        }else if(tipoRecibo.equals("locacion")){
            recibo.setIdentificadorLocacion(numeroSinCeros);
        }
        
        
        recibo.setPdf(archivo);
        
        reciboRepo.save(recibo);
        
        Pago pago = pagoServicio.getOne(idPago);
        
        pago.setRecibo(recibo);
        pago.setRealizado(true);
        
        pagoServicio.guardaPago(pago);
        
    }
    
   
    
}
