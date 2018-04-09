/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain.dto;

import com.proycc.base.domain.Cliente;
import com.proycc.base.domain.Operacion;
import com.proycc.base.domain.OperacionItem;

/**
 *
 * @author fafre
 */
public class OperacionDTO {
    
    private Operacion operacion;
    private Cliente cliente;
    private OperacionItem opO;
    private OperacionItem opD;
    private boolean modificable;
    private boolean procesado;
    private boolean readOnly;

    public OperacionDTO(Operacion operacion, Cliente cliente, OperacionItem opO, OperacionItem opD) {
        this.operacion = operacion;
        this.cliente = cliente;
        this.opO = opO;
        this.opD = opD;
    }



    public OperacionDTO() {
    }
    

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

  
    public boolean isModificable() {
        return modificable;
    }

    public void setModificable(boolean modificable) {
        this.modificable = modificable;
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    public OperacionItem getOpO() {
        return opO;
    }

    public void setOpO(OperacionItem opO) {
        this.opO = opO;
    }

    public OperacionItem getOpD() {
        return opD;
    }

    public void setOpD(OperacionItem opD) {
        this.opD = opD;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    

    @Override
    public String toString() {
        return "OperacionDTO{" + "operacion=" + operacion + ", cliente=" + cliente  + ", modificable=" + modificable + ", procesado=" + procesado + '}';
    }

    
    
    
}
