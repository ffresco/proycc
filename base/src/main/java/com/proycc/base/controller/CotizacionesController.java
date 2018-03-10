/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.controller;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.DataMaster;
import com.proycc.base.service.CotizacionService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        ModelAndView mav = new ModelAndView("cotizaciones");
        List<Cotizacion> cotizaciones = cotizacionService.getAll();
        LOGGER.debug("listado de cotizaciones " + cotizaciones);
        mav.addObject("cotizaciones", cotizaciones);
        return mav;
    }

    @RequestMapping(value = "/cotizaciones/create", method = RequestMethod.GET)
    public ModelAndView getCotizacionCreatePage() {
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setFecha(LocalDateTime.now());
        cotizacion.setMonedaBase(dataMaster.getMonedas().get(0));
        cotizacion.setComisionCmp(Float.MIN_NORMAL);
        cotizacion.setComisionVta(Float.MIN_NORMAL);
        cotizacion.setCotizacionCmp(Float.MIN_VALUE);
        cotizacion.setCotizacionVta(Float.MIN_VALUE);
        System.out.println("Estoy en la cotizacion cotroller");
        LOGGER.info("aca estamos en controller");
        ModelAndView mav = new ModelAndView("cotizacion_create");
        mav.addObject("cotizacion", cotizacion);
        mav.addObject("dataMaster", dataMaster);
        LOGGER.info("ests es el mav " + mav.toString());
        LOGGER.info(dataMaster.toString());
        return mav;
    }
    
    @RequestMapping(value = "/cotizaciones/save",method = RequestMethod.POST)
    public ModelAndView saveCotizacion(@Valid @ModelAttribute("cotizacion") Cotizacion cot,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            LOGGER.debug(bindingResult.toString());
        }
        LOGGER.debug("Cotizacion grabada "+ cotizacionService.saveOrUpdate(cot));
        LOGGER.debug("grabe una cotizacion");
        return getMainPage();
    }
    
    @RequestMapping(value = "/cotizaciones/delete/{id}")
    public ModelAndView deleteCotizacion(@PathVariable Long id){
        cotizacionService.delete(cotizacionService.getById(id));
        return getMainPage();
    }
    
    @RequestMapping(value = "/cotizaciones/edit/{id}")
    public ModelAndView editCotizacion(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("cotizacion_create");
        mav.addObject("cotizacion", cotizacionService.getById(id));
        mav.addObject("dataMaster", dataMaster);
        LOGGER.info("ests es el mav " + mav.toString());
        LOGGER.info(dataMaster.toString());
        return mav;
 
        
    }

}
