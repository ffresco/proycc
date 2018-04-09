/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.controller;

import com.proycc.base.domain.Cliente;
import com.proycc.base.repository.ClienteRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EntidadesController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EntidadesController.class);
    
    @Autowired
    private ClienteRepository cliRepo;

    @RequestMapping(value = "/entidades")
    public ModelAndView findAll() {
        return new ModelAndView("entidades", "clientes", cliRepo.findAll());
    }

    @RequestMapping(value = "/savecli", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("cliente") Cliente cli) {
        cliRepo.save(cli);
        return new ModelAndView("listado","clientes",cliRepo.findAll());
    }

    @RequestMapping(value="/addcli")
    public ModelAndView add(Cliente cliente) {
        
        return new ModelAndView("addcli", "cliente",cliente);
    
    }

    @RequestMapping(value="/editcli/")
    public ModelAndView edit(@RequestParam Map<String,String> parametros) {
        System.out.println(parametros.get("documento"));
        return add(cliRepo.findByDocumento(parametros.get("documento")));
    }
    
    @RequestMapping(value="/deletecli/{documento}")
    public ModelAndView delete(@PathVariable("documento") String documento) {
        cliRepo.delete(cliRepo.findByDocumento(documento));
        return findAll();        
    
    }
    

}
