/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.controller;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.DataMaster;
import com.proycc.base.service.CotizacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fafre
 */
@Controller
public class CotizacionesController {

    private final Logger LOGGER = LoggerFactory.getLogger(CotizacionesController.class);
    private final CotizacionService cotizacionService;
    private final DataMaster dataMaster;

    @Autowired
    public CotizacionesController(CotizacionService cotizacionService, DataMaster dataMaster) {
        this.cotizacionService = cotizacionService;
        this.dataMaster = dataMaster;
    }

    @RequestMapping("/cotizaciones")
    public ModelAndView getMainPage() {
        return new ModelAndView("cotizaciones");
    }

    @RequestMapping(value = "/cotizaciones/create", method = RequestMethod.GET)
    public ModelAndView getCotizacionCreatePage() {
        Cotizacion cotizacion = new Cotizacion();
        System.out.println("Estoy en la cotizacion cotroller");
        LOGGER.info("aca estamos en controller");
        ModelAndView mav = new ModelAndView("cotizacion_create");
        mav.addObject("cotizacion", cotizacion);
        mav.addObject("dataMaster", dataMaster);
        LOGGER.info("ests es el mav " + mav.toString());
        LOGGER.info(dataMaster.toString());
        return mav;
    }

}
