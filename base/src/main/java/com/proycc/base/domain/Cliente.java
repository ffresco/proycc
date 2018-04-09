/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author fafre
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "documento")
    private String documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "edad")
    private int edad;
   
    @Column(name = "fecha_nac")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNac;
    
    @OneToOne(mappedBy = "cliente",cascade = CascadeType.PERSIST,optional = false,fetch = FetchType.EAGER,orphanRemoval = true)
    private AcumuladoCliente acumulado;

    public Cliente() {
    }

    public Cliente(String documento, String Nombre, String Apellido, String email,
            int edad, Date fechaNac, AcumuladoCliente ac) {
        this.documento = documento;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.email = email;
        this.edad = edad;
        this.fechaNac = fechaNac;
        this.acumulado = ac;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
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

    public AcumuladoCliente getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(AcumuladoCliente acumulado) {
        this.acumulado = acumulado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", edad=" + edad + ", fechaNac=" + fechaNac + ", acumulado=" + acumulado + '}';
    }
    
    
    
}
