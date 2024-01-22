/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.repositorios;

import com.blasdsaidon.fincasdellitoral.entidades.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author blasd
 */
@Repository
public interface ReciboRepositorio extends JpaRepository<Recibo, String> {
    
        // Consulta nativa para obtener el identificadorHonorario más grande
    @Query(value = "SELECT MAX(identificador_honorario) FROM Recibo", nativeQuery = true)
    int encontrarMaxIdentificadorHonorario();

    // Consulta nativa para obtener el identificadorLocacion más grande
    @Query(value = "SELECT MAX(identificador_locacion) FROM Recibo", nativeQuery = true)
    int encontrarMaxIdentificadorLocacion();
}

