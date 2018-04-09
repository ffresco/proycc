/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fafre
 */
@Entity
@Table(name = "parametros")
public class Parametro implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")    
    private Long id;
    
    @Column(name = "valor")
    private String valor;
    
    @Column(name = "tipo")
    private String tipo;

    public Parametro() {
    }

    public Parametro(String valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Parametro{" + "id=" + id + ", valor=" + valor + ", tipo=" + tipo + '}';
    }
    
    
    

    
    
    
}
