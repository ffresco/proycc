/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.configuration;

import com.proycc.base.domain.Parametro;
import com.proycc.base.domain.TopeCompra;
import com.proycc.base.repository.ParametroRepo;
import com.proycc.base.repository.TopesRepo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author fafre
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataMaster {

    private List<Parametro> monedas;
    private List<Parametro> entidades;
    private List<Parametro> tipoCambios;
    private List<Parametro> tipoOperaciones;
    private List<Parametro> instrumentos;
    private List<TopeCompra> topes;
    private Parametro monedaBase;
    private List<Parametro> cajas;
    private Parametro movIng;
    private Parametro movEgr;
    private Long idMBase;
    private TopeCompra topeVigente;

    //creado para pantalla operaciones especialmente
    private List<Parametro> tipoSubOps;

    public static final String MONEDAS = "MONEDAS";
    public static final String ENTIDADES = "ENTIDADES";
    public static final String TIPO_CAMBIOS = "TIPOS_CAMBIOS";
    public static final String TIPO_OPERACIONES = "TIPO_OPERACIONES";
    public static final String INSTRUMENTOS = "INSTRUMENTOS";
    public static final String TOPES = "TOPES";
    public static final String MONEDA_BASE_ID = "MONEDA_BASE_ID";
    public static final String CAJA = "CAJA";
    public static final String TIPO_MOV_IN = "TIPO_MOV_INGRESO";
    public static final String TIPO_MOV_EG = "TIPO_MOV_EGRESO";
    public static final String TIPO_OP_A_COMBINAR = "CMP-VTA";

    private final ParametroRepo parametroRepo;
    private final TopesRepo topesRepo;

    @Autowired
    public DataMaster(ParametroRepo parametroRepo, TopesRepo tr) {
        this.parametroRepo = parametroRepo;
        this.topesRepo = tr;

    }

    public void intiData() {

        this.monedas = parametroRepo.findByTipo(MONEDAS);
        this.entidades = parametroRepo.findByTipo(ENTIDADES);
        this.tipoCambios = parametroRepo.findByTipo(TIPO_CAMBIOS);
        this.tipoOperaciones = parametroRepo.findByTipo(TIPO_OPERACIONES);
        this.instrumentos = parametroRepo.findByTipo(INSTRUMENTOS);
        this.topes = topesRepo.findAllByOrderByFechaAltaDesc();
        this.idMBase = Long.parseLong(parametroRepo.findByTipo(MONEDA_BASE_ID).get(0).getValor());
        this.monedaBase = parametroRepo.findOne(this.idMBase);
        this.cajas = parametroRepo.findByTipo(CAJA);
        this.movIng = parametroRepo.findByTipo(TIPO_MOV_IN).get(0);
        this.movEgr = parametroRepo.findByTipo(TIPO_MOV_EG).get(0);
        this.tipoSubOps = parametroRepo.findByTipo(TIPO_OPERACIONES);
        this.topeVigente = this.topes.get(0);
        

    }

    public List<Parametro> getMonedas() {
        return monedas;
    }

    public List<Parametro> getEntidades() {
        return entidades;
    }

    public List<Parametro> getTipoCambios() {
        return tipoCambios;
    }

    public List<Parametro> getTipoOperaciones() {
        return tipoOperaciones;
    }

    public List<Parametro> getInstrumentos() {
        return instrumentos;
    }

    public List<TopeCompra> getTopes() {
        return topes;
    }

    public Parametro getMonedaBase() {
        return monedaBase;
    }

    public List<Parametro> getCajas() {
        return cajas;
    }

    public void setCajas(List<Parametro> cajas) {
        this.cajas = cajas;
    }

    public Parametro getMovIng() {
        return movIng;
    }

    public void setMovIng(Parametro movIng) {
        this.movIng = movIng;
    }

    public Parametro getMovEgr() {
        return movEgr;
    }

    public void setMovEgr(Parametro movEgr) {
        this.movEgr = movEgr;
    }

    public Long getIdMBase() {
        return idMBase;
    }

    public void setIdMBase(Long idMBase) {
        this.idMBase = idMBase;
    }

    public List<Parametro> getTipoSubOps() {
        return tipoSubOps;
    }

    public void setTipoSubOps(List<Parametro> tipoSubOps) {
        this.tipoSubOps = tipoSubOps;
    }

    public TopeCompra getTopeVigente() {
        return topeVigente;
    }

    public void setTopeVigente(TopeCompra topeVigente) {
        this.topeVigente = topeVigente;
    }

    private void quitarDuplicados(List<Parametro> tipoOperaciones) {
        String ant = "";
        Iterator<Parametro> it = tipoOperaciones.iterator();
        while (it.hasNext()) {
            Parametro nuevo = it.next();
            String nuevoValor = nuevo.getValor();
            if(nuevo.getValor().equals(ant)){
                it.remove();
            }
            ant=nuevoValor;
            
        }

    }

}
