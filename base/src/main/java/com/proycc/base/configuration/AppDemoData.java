/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.configuration;

import com.proycc.base.controller.CotizacionesController;
import com.proycc.base.domain.AcumuladoCliente;
import com.proycc.base.domain.Cliente;
import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.DataMaster;
import com.proycc.base.repository.ClienteRepository;
import com.proycc.base.repository.CotizacionRepository;
import com.proycc.base.service.CotizacionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fafre
 */
@Service
public class AppDemoData {

    private ClienteRepository cliR;
    private CotizacionService cotS;

    @Autowired
    public AppDemoData(ClienteRepository cliR,  CotizacionService cotS) {
        this.cliR = cliR;
        this.cotS = cotS;
    }

    public void initAppData() {
        
        //Acumulado
        AcumuladoCliente ac1 = new AcumuladoCliente(new Long(1),new Float(20), new Float(100), Month.MARCH, Year.of(2018), LocalDate.of(2018,12,31));

        
        /*******Clientes data***********/
        Cliente c1 = new Cliente("11", "Rodrigo", "Suarez", "rsuarez@cc.com", 38, new Date());
        Cliente c2 = new Cliente("12", "Tito", "Sixto", "tito@cc.com", 38, new Date());
        Cliente c3 = new Cliente("13", "Fernando", "Fresco", "ffresco.com", 38, new Date());
        c1.setAcumulado(ac1);
        c2.setAcumulado(ac1);
        c3.setAcumulado(ac1);
        cliR.addCli(c1);
        cliR.addCli(c2);
        cliR.addCli(c3);

        
        /*******Cotizaciones+++++++++++++++*/
        Cotizacion cot1 = new Cotizacion(LocalDateTime.of(2018, Month.MAY, 30, 0, 0), "USD", "EMPRESA", "AGENCIA", new Float(10), new Float(11), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot2 = new Cotizacion(LocalDateTime.of(2018, Month.MAY, 29, 0, 0), "USD", "EMPRESA", "AGENCIA", new Float(9), new Float(10), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot3 = new Cotizacion(LocalDateTime.of(2018, Month.MAY, 28, 0, 0), "USD", "EMPRESA", "AGENCIA", new Float(8), new Float(9), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot4 = new Cotizacion(LocalDateTime.of(2018, Month.MAY, 30, 0, 0), "REAL", "EMPRESA", "AGENCIA", new Float(8), new Float(9), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot5 = new Cotizacion(LocalDateTime.of(2018, Month.MAY, 1, 0, 0), "REAL", "EMPRESA", "AGENCIA", new Float(8), new Float(9), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
                
        cotS.saveOrUpdate(cot1);
        cotS.saveOrUpdate(cot2);
        cotS.saveOrUpdate(cot3);
        cotS.saveOrUpdate(cot4);
        
  
         
         
    }
}