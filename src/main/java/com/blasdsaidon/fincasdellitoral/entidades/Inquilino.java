/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blasdsaidon.fincasdellitoral.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author blasd
 */
@Entity
public class Inquilino extends Persona{

    public Inquilino() {
    }

    @Override
    public String toString() {
        return "Inquilino{" + '}';
    }
    
    
}
