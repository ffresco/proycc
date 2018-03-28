/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain;

import java.io.Serializable;

/**
 *
 * @author fafre
 */
public class TopeCompra implements Serializable {
    
    private Long id;
    private String tipo;
    private Float tope;
    //Agregar una entidad cuando reodri termine

    public TopeCompra(Long id, String tipo, Float tope) {
        this.id = id;
        this.tipo = tipo;
        this.tope = tope;
    }

    public TopeCompra() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getTope() {
        return tope;
    }

    public void setTope(Float tope) {
        this.tope = tope;
    }
    
    
}
