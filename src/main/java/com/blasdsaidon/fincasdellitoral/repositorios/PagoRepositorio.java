/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.repositorios;

import com.blasdsaidon.fincasdellitoral.entidades.ContratoHonorariosDTO;
import com.blasdsaidon.fincasdellitoral.entidades.Pago;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *@Query("SELECT t FROM turno t WHERE t.medico.idPersona = :idPersona ORDER BY t.fecha DESC")
     public Collection<turno> buscarTurnoPorMedico(@Param("idPersona") String idPersona);
 * @author blasd
 */
@Repository
public interface PagoRepositorio extends JpaRepository<Pago, String> {
    
 @Query("SELECT p FROM Pago p WHERE p IN :pagos ORDER BY p.mesAno ")
    List<Pago> findSortedByMesAno(@Param("pagos") Collection<Pago> pagos);
  
    
     @Query(nativeQuery = true, value =            
            "SELECT p.mes_ano, p.numero_cuota, c.num_contrato, p.descuento_hono " +
        "FROM contrato_honorarios ch " +
        "JOIN pago p ON ch.honorarios_id_pago = p.id_pago " +
        "JOIN contrato c ON ch.contrato_id_contrato = c.id_contrato " +
        "WHERE ch.honorarios_id_pago IS NOT NULL " +
        "AND p.descuento_hono > 0 " +
        "AND p.realizado = true " +
        "AND (" +
        "(YEAR(p.mes_ano) = YEAR(CURRENT_DATE) AND MONTH(p.mes_ano) = MONTH(CURRENT_DATE)) " +
        "OR " +
        "(YEAR(p.mes_ano) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(p.mes_ano) = 12)" +
        ") " +
        "ORDER BY p.mes_ano, p.numero_cuota")
    List<Object[]> obtenerDatosContratoHonorarios();
   
}
