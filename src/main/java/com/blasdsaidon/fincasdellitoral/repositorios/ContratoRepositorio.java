/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.repositorios;

import com.blasdsaidon.fincasdellitoral.entidades.Contrato;
import com.blasdsaidon.fincasdellitoral.entidades.Inquilino;
import com.blasdsaidon.fincasdellitoral.entidades.Propietario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author blasd
 */
@Repository
public interface ContratoRepositorio extends JpaRepository<Contrato, String> {
   
    @Query("SELECT c FROM Contrato c JOIN c.codeudores co WHERE co.idPersona = :idPersona")
    List<Contrato> findContratosByCodeudorId(@Param("idPersona") String idPersona);
    
    @Query("SELECT c FROM Contrato c JOIN c.inquilino ci WHERE ci.idPersona = :idPersona")
    List<Contrato> findContratosByInquilinoId(@Param("idPersona") String idPersona);
    
    @Query("SELECT c FROM Contrato c JOIN c.propietario cp WHERE cp.idPersona = :idPersona")
    List<Contrato> findContratosByPropietarioId(@Param("idPersona") String idPersona);
    
    
    List<Contrato> findAllByOrderByNumContratoAsc();


}
