/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain.dto;

/**
 *
 * @author fafre
 */
public class TextFileDTO {
    private String nombre;
    private String documento;
    private String dni;
    private float monto;

    public TextFileDTO(String nombre, String documento, String dni, float monto) {
        this.nombre = nombre;
        this.documento = documento;
        this.dni = dni;
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    
            
}
