/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.util.SerializationUtils;

/**
 *
 * @author fafre
 */
@Entity
@Table(name="cotizacion")
public class Cotizacion implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="fecha")    
    private LocalDateTime fecha;
    
    @Column(name="moneda")
    private String moneda;
    
    @Column(name="entidad")
    private String entidad;
    
    @Column(name="tipoCambio")
    private String tipoCambio;
    
    @Column(name="cotizacion_vta")
    private Float cotizacionVta;
    
    @Column(name="cotizacion_cmp")
    private Float cotizacionCmp;
    
    @Column(name="comision_vta")
    private Float comisionVta;
    
    @Column(name="comision_cmp")
    private Float comisionCmp;
    
    @Column(name="instrumento")
    private String instrumento;
    
    @Column(name="tipo_op")
    private String tipoOp;
    
    @Column(name="moneda_base")
    private String monedaBase;
    
    private boolean compra;

    public Cotizacion(LocalDateTime fecha, String moneda, String entidad, String tipoCambio, Float cotizacionVta, Float cotizacionCmp, Float comisionVta, Float comisionCmp, String instrumento, String tipoOp, String monedaBase) {
    
        this.fecha = fecha;
        this.moneda = moneda;
        this.entidad = entidad;
        this.tipoCambio = tipoCambio;
        this.cotizacionVta = cotizacionVta;
        this.cotizacionCmp = cotizacionCmp;
        this.comisionVta = comisionVta;
        this.comisionCmp = comisionCmp;
        this.instrumento = instrumento;
        this.tipoOp = tipoOp;
        this.monedaBase = monedaBase;
    }

    public Cotizacion() {
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }



    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Float getCotizacionVta() {
        return cotizacionVta;
    }

    public void setCotizacionVta(Float cotizacionVta) {
        this.cotizacionVta = cotizacionVta;
    }

    public Float getCotizacionCmp() {
        return cotizacionCmp;
    }

    public void setCotizacionCmp(Float cotizacionCmp) {
        this.cotizacionCmp = cotizacionCmp;
    }

    public Float getComisionVta() {
        return comisionVta;
    }

    public void setComisionVta(Float comisionVta) {
        this.comisionVta = comisionVta;
    }

    public Float getComisionCmp() {
        return comisionCmp;
    }

    public void setComisionCmp(Float comisionCmp) {
        this.comisionCmp = comisionCmp;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getTipoOp() {
        return tipoOp;
    }

    public void setTipoOp(String tipoOp) {
        this.tipoOp = tipoOp;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public boolean isCompra() {
        return compra;
    }

    public void setCompra(boolean compra) {
        this.compra = compra;
    }

    @Override
    public String toString() {
        return "Cotizacion{" + "id=" + id + ", fecha=" + fecha + ", moneda=" + moneda + ", entidad=" + entidad + ", tipoCambio=" + tipoCambio + ", cotizacionVta=" + cotizacionVta + ", cotizacionCmp=" + cotizacionCmp + ", comisionVta=" + comisionVta + ", comisionCmp=" + comisionCmp + ", instrumento=" + instrumento + ", tipoOp=" + tipoOp + ", monedaBase=" + monedaBase + ", compra=" + compra + '}';
    }
    
    



 
    

    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
