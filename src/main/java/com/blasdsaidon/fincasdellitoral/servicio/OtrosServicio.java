/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.servicio;

import com.blasdsaidon.fincasdellitoral.entidades.Otros;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import com.blasdsaidon.fincasdellitoral.repositorios.PagoRepositorio;
import com.blasdsaidon.fincasdellitoral.repositorios.OtrosRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author blasd
 */
@Service
public class OtrosServicio {
    
    
    @Autowired
    private OtrosRepositorio otrosRepo;
    
    public List<Otros> crearOtros(){
        List<Otros> otrosList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Otros otro = new Otros();
            otrosRepo.save(otro);
            otrosList.add(otro);
        }
        
        
       
      return otrosList;  
        
    }
    
    public ArrayList<Otros> insertarOtros(String concepto1, String concepto2, String concepto3, Double monto1, Double monto2, Double monto3){
        Otros otro1 = new Otros();
        Otros otro2 = new Otros();
        Otros otro3 = new Otros();
        if (concepto1!=null && monto1!=null) {
            otro1.setConcepto(concepto1);
            otro1.setMonto(monto1);
            otro1.setOrden(1);
        }
        if (concepto2!=null && monto2!=null) {
            otro2.setConcepto(concepto2);
            otro2.setMonto(monto2);
            otro2.setOrden(2);
        }
        if (concepto3!=null && monto3!=null) {
            otro3.setConcepto(concepto3);
            otro3.setMonto(monto3);
            otro3.setOrden(3);
        }
        
        otrosRepo.save(otro1);
        otrosRepo.save(otro2);
        otrosRepo.save(otro3);
        
        ArrayList<Otros> listaOtros = new ArrayList();
        
        listaOtros.add(otro1);
        listaOtros.add(otro2);
        listaOtros.add(otro3);
        
        
        return listaOtros;
    }
}
