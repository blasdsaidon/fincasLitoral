/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.repositorios;

import com.blasdsaidon.fincasdellitoral.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author blasd
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    public Usuario buscarPorNombre(@Param("nombreUsuario") String nombreUsuario);
    
    @Query("SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    public Usuario buscarPorId(@Param("idUsuario") String idUsuario);
}
