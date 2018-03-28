/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 *
 * @author fafre
 */
public class AcumuladoCliente {
    private Long id;
    private Float mensual;
    private Float anual;
    private Month mes;
    private Year ano;
    private LocalDate fechaHasta;

    public AcumuladoCliente(Long id, Float mensual, Float anual, Month mes, Year ano, LocalDate fechaHasta) {
        this.id = id;
        this.mensual = mensual;
        this.anual = anual;
        this.mes = mes;
        this.ano = ano;
        this.fechaHasta = fechaHasta;
    }


    public Float getMensual() {
        return mensual;
    }

    public void setMensual(Float mensual) {
        this.mensual = mensual;
    }

    public Float getAnual() {
        return anual;
    }

    public void setAnual(Float anual) {
        this.anual = anual;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Month getMes() {
        return mes;
    }

    public void setMes(Month mes) {
        this.mes = mes;
    }

    public Year getAno() {
        return ano;
    }

    public void setAno(Year ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "AcumuladoCliente{" + "id=" + id + ", mensual=" + mensual + ", anual=" + anual + ", mes=" + mes + ", ano=" + ano + ", fechaHasta=" + fechaHasta + '}';
    }

    
    
    
    
}
