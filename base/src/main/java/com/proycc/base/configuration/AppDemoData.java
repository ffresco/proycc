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
import com.proycc.base.domain.Parametro;
import com.proycc.base.repository.ClienteRepository;
import com.proycc.base.repository.CotizacionRepository;
import com.proycc.base.repository.ParametroRepo;
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
    private ParametroRepo pr;

    @Autowired
    public AppDemoData(ClienteRepository cliR,  CotizacionService cotS, ParametroRepo pr) {
        this.cliR = cliR;
        this.cotS = cotS;
        this.pr = pr;
    }

    public void initAppData() {
      
        //Alta de parametros
        Parametro caja = pr.save(new Parametro("CAJA-1","CAJA"));
        Parametro tipoOp = pr.save(new Parametro("CMP-VTA","TIPO_OPERACION"));
        pr.save(new Parametro("ARBITRAJE","TIPO_OPERACION"));
        pr.save(new Parametro("CANJE","TIPO_OPERACION"));
        Parametro tipoCam = pr.save(new Parametro("AGENCIA","TIPO_CAMBIO"));
        
        //instrumentos
        Parametro instrumento = pr.save(new Parametro("BILLETES","INSTRUMENTO"));        
        pr.save(new Parametro("CHEQUE","INSTRUMENTO"));
        //tipos movimeiento
        Parametro mov = pr.save(new Parametro("INGRESO","TIPO_MOVIMIENTO"));
        Parametro egreso =pr.save(new Parametro("EGRESO","TIPO_MOVIMIENTO"));
        
        //monedas
        Parametro moneda = pr.save(new Parametro("USD","MONEDA"));
        Parametro monedaBase = pr.save(new Parametro("AR$","MONEDA"));
        Parametro real = pr.save(new Parametro("REAL","MONEDA"));
        //estados
        pr.save(new Parametro("NUEVO","ESTADO"));
        pr.save(new Parametro("PROCESADO","ESTADO"));
        pr.save(new Parametro("OK","ESTADO"));
        pr.save(new Parametro("OBSERVADO","ESTADO"));
        
        
        
          
        
        /*******Clientes data***********/
        Cliente c1 = new Cliente("27444999", "Rodrigo", "Suarez", "rsuarez@cc.com", 38, new Date(),null);
        //Acumulado siempre debo poner el padre en el dueño de la realacion, para que me guarde la relacion
        AcumuladoCliente ac1 = new AcumuladoCliente(c1, Month.MAY, 0, new Float(20), 
                new Float(100), moneda, LocalDateTime.now());
        ac1.setCliente(c1);
        Cliente c2 = new Cliente("22478700", "Tito", "Sixto", "tito@cc.com", 38, new Date(),null);
        Cliente c3 = new Cliente("28478718", "Fernando", "Fresco", "ffresco.com", 38, new Date(),null);
        c1.setAcumulado(ac1);
   //     c2.setAcumulado(ac1);
   //     c3.setAcumulado(ac1);
        cliR.save(c1);
        cliR.save(c2);
        cliR.save(c3);

        
        
        /*******Cotizaciones+++++++++++++++*/
        Cotizacion cot1 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 27, 0, 0), "USD", "EMPRESA", "AGENCIA", new Float(10), new Float(11), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot2 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 26, 0, 0), "USD", "EMPRESA", "AGENCIA", new Float(9), new Float(10), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot3 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 25, 0, 0), "USD", "EMPRESA", "AGENCIA", new Float(8), new Float(9), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot4 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 3, 0, 0), "REAL", "EMPRESA", "AGENCIA", new Float(8), new Float(9), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
        Cotizacion cot5 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 1, 0, 0), "REAL", "EMPRESA", "AGENCIA", new Float(8), new Float(9), 
                new Float(0.1), new Float(0.12), "BILLETES", "CMP-VTA", "AR$");
                
        cotS.saveOrUpdate(cot1);
        cotS.saveOrUpdate(cot2);
        cotS.saveOrUpdate(cot3);
        cotS.saveOrUpdate(cot4);
        
  
         
         
    }
}