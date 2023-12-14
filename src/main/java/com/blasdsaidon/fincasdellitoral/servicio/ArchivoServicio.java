package com.blasdsaidon.fincasdellitoral.servicio;


import com.blasdsaidon.fincasdellitoral.entidades.Archivo;
import com.blasdsaidon.fincasdellitoral.repositorios.ArchivoRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author blasd
 */
@Service
public class ArchivoServicio {
    @Autowired
    private ArchivoRepositorio archivoRepo;
    
    public Collection<Archivo> guardar(List<MultipartFile> archivos) throws Exception{
        
        Collection<Archivo> archivosNueva = new ArrayList<>();
        if(archivos!=null){
        for (MultipartFile file : archivos) {
                try {
                    Archivo archivo = new Archivo();
                    archivo.setMime(file.getContentType());
                    archivo.setNombre(file.getOriginalFilename());
                    archivo.setContenido(file.getBytes());
                    archivoRepo.save(archivo);
                    archivosNueva.add(archivo);
                } catch (IOException e) {
                    // Manejar la excepción, por ejemplo, registrándola o lanzándola nuevamente
                    e.printStackTrace();
                }
            }
        
        }
        return archivosNueva;
}
}
