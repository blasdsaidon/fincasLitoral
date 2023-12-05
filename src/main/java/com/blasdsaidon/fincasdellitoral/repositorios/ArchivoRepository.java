/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.repositorios;

import com.blasdsaidon.fincasdellitoral.entidades.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author blasd
 */
@Repository 
public interface ArchivoRepository extends JpaRepository<Archivo, String> {
    
}