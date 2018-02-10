/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.controller;

import com.proycc.base.entitys.Cliente;
import com.proycc.base.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository cliRepo;

    @RequestMapping(value = "/listadocli")
    public ModelAndView findAll() {
        return new ModelAndView("listado", "clientes", cliRepo.getClientes());
    }

    @RequestMapping(value = "/savecli", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("cliente") Cliente cli) {
        cliRepo.addCli(cli);
        return new ModelAndView("listado","clientes",cliRepo.getClientes());
    }

    @RequestMapping(value="/addcli")
    public ModelAndView add(Cliente cliente) {
        
        return new ModelAndView("addcli", "cliente",cliente);
    
    }

    @RequestMapping(value="/editcli/{documento}")
    public ModelAndView edit(@PathVariable("documento") String documento) {
        return add(cliRepo.getById(documento));
        
    
    }
    
    @RequestMapping(value="/deletecli/{documento}")
    public ModelAndView delete(@PathVariable("documento") String documento) {
        cliRepo.removeCli(cliRepo.getById(documento));
        return findAll();
        
    
    }
    

}
