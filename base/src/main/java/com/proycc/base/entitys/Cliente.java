/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.entitys;

import java.util.Date;

/**
 *
 * @author fafre
 */
public class Cliente {
    
    private String documento;
    private String Nombre;
    private String Apellido;
    private String email;
    private int edad;
    private Date fechaNac;

    public Cliente() {
    }

    public Cliente(String documento, String Nombre, String Apellido, String email, int edad, Date fechaNac) {
        this.documento = documento;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.email = email;
        this.edad = edad;
        this.fechaNac = fechaNac;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    
}
