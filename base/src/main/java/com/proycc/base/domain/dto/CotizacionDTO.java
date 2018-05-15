/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain.dto;

import com.proycc.base.domain.*;
import java.io.Serializable;


/**
 *
 * @author fafre
 */

public class CotizacionDTO implements Serializable{
    
    private Cotizacion cotizacion;

    public CotizacionDTO() {
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }
 
   
    
}
