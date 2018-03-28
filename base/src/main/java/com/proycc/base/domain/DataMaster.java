/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private final List<String> monedas; 
    private final List<String> entidades; 
    private final List<String> tipoCambios;
    private final List<String> tipoOperaciones;
    private final List<String> instrumentos;
    private final List<TopeCompra> topes;
    private final String monedaBase;

    public DataMaster() {
        this.tipoOperaciones = Arrays.asList("CPRA-VTA","ARBITRAJE","CANJE");
        this.entidades =  Arrays.asList("EMPRESA","CLIENTE1");
        this.tipoCambios = Arrays.asList("AGENCIA","CAMBISTA","ESPECIAL");
        this.monedas = Arrays.asList("USD","AR$","REAL");
        this.instrumentos = Arrays.asList("BILLETE","CHEQUE");
        this.monedaBase ="AR$";
        this.topes = new ArrayList();
        this.topes.add(new TopeCompra(new Long(1), "MENSUAL", new Float (10000)));
        this.topes.add(new TopeCompra(new Long(1), "A", new Float (50000)));
    }
                

    public List<String> getMonedas() {
        return monedas;
    }

    public List<String> getEntidades() {
        return entidades;
    }

    public List<String> getTipoCambios() {
        return tipoCambios;
    }

    public List<String> getTipoOperaciones() {
        return tipoOperaciones;
    }

    public List<String> getInstrumentos() {
        return instrumentos;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    
    
    @Override
    public String toString() {
        return "DataMaster{" + "monedas=" + monedas + ", entidades=" + entidades + ", tipoCambios=" + tipoCambios + ", tipoOperaciones=" + tipoOperaciones + ", instrumentos=" + instrumentos + '}';
    }
    
    
    
    
    
    
}
