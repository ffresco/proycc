/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.controller;

import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author fafre
 */
@Controller
public class CajasController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CajasController.class);

    @RequestMapping("/cajas")
    public ModelAndView getMainPage() {
        return new ModelAndView("cajas");
    }

}
