/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author blasd
 */
@Entity
public class Propietario extends Persona {

    public Propietario() {
    }

    @Override
    public String toString() {
        return "Propietario{" + '}';
    }
    
    
    
}
