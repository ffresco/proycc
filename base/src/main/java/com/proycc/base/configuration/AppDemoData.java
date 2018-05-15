/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.configuration;

import com.proycc.base.controller.CotizacionesController;
import com.proycc.base.domain.AcumuladoCaja;
import com.proycc.base.domain.AcumuladoCliente;
import com.proycc.base.domain.Cliente;
import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.Parametro;
import com.proycc.base.domain.Role;
import com.proycc.base.domain.SesionCaja;
import com.proycc.base.domain.TopeCompra;
import com.proycc.base.domain.User;
import com.proycc.base.repository.AcumuladoCajaRepo;
import com.proycc.base.repository.ClienteRepository;
import com.proycc.base.repository.CotizacionRepository;
import com.proycc.base.repository.ParametroRepo;
import com.proycc.base.repository.SesionCajaRepo;
import com.proycc.base.repository.TopesRepo;
import com.proycc.base.repository.UserRepo;
import com.proycc.base.service.CotizacionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private TopesRepo topesRepo;
    private DataMaster dm;
    private UserRepo ur;
    private SesionCajaRepo scr;
    private AcumuladoCajaRepo acr;

    @Autowired
    public AppDemoData(ClienteRepository cliR, CotizacionService cotS,
            ParametroRepo pr, TopesRepo tr, DataMaster dm, UserRepo ur, SesionCajaRepo scr,
            AcumuladoCajaRepo acr) {
        this.cliR = cliR;
        this.cotS = cotS;
        this.pr = pr;
        this.topesRepo = tr;
        this.dm = dm;
        this.ur = ur;
        this.scr = scr;
        this.acr = acr;
                
    }

    public void initAppData() {

        //usuarios
        User fer =ur.save(new User("ffresco@gd","1",Role.ADMIN));
        User rodri =ur.save(new User("mulo@mulo.com", "1", Role.USER));
        ur.save(new User("ffresco@gmail.com", "1", Role.ADMIN));
        User cin= ur.save(new User("czinna@clg.com.ar","1",Role.ADMIN));
      
      
        /**Parametro*****************************/
        Parametro pOpCmpVta = pr.save(new Parametro("VTA","", DataMaster.TIPO_OPERACIONES));
        Parametro pOpCmpVta2 = pr.save(new Parametro("CMP","", DataMaster.TIPO_OPERACIONES));
        Parametro pOpArb= pr.save(new Parametro("ARBITRAJE", "",DataMaster.TIPO_OPERACIONES));
        Parametro pOpCanje= pr.save(new Parametro("CANJE", "",DataMaster.TIPO_OPERACIONES));
        Parametro pEntidad= pr.save(new Parametro("EMPRESA", DataMaster.ENTIDADES));
        Parametro pTcMin= pr.save(new Parametro("MINORISTA", DataMaster.TIPO_CAMBIOS));
        Parametro pTcesp= pr.save(new Parametro("PREFER-1", DataMaster.TIPO_CAMBIOS));

        
        Parametro pMUsd= pr.save(new Parametro("USD", DataMaster.MONEDAS));
        Parametro pMEur= pr.save(new Parametro("EUR", DataMaster.MONEDAS));
        Parametro pMReal= pr.save(new Parametro("REAL", DataMaster.MONEDAS));
        Parametro pMArs= pr.save(new Parametro("AR$", DataMaster.MONEDAS));
        Parametro pIBillete= pr.save(new Parametro("BILLETE", DataMaster.INSTRUMENTOS));
        Parametro pICheque = pr.save(new Parametro("CHEQUE", DataMaster.INSTRUMENTOS));
        Parametro pCaja = pr.save(new Parametro("CAJA-1", DataMaster.CAJA));
        Parametro pCaja2 = pr.save(new Parametro("CAJA-2", DataMaster.CAJA));
        Parametro pCaja3 = pr.save(new Parametro("CAJA-3", DataMaster.CAJA));
        pr.save(new Parametro("TESORO",DataMaster.CAJA));
        pr.save(new Parametro("T_EXT",DataMaster.CAJA));        
        Parametro pMovIng = pr.save(new Parametro("INGRESO", DataMaster.TIPO_MOV_IN));
        Parametro pMovEg = pr.save(new Parametro("EGRESO", DataMaster.TIPO_MOV_EG));
        
        //de la moneda base guardo el id
        Parametro pMBase = pr.save(new Parametro(pMArs.getId().toString(), DataMaster.MONEDA_BASE_ID));
        
        //Asociacion a cajas
        System.out.println("probando eesto sesion caja");
        scr.save(new SesionCaja(fer, pCaja, LocalDateTime.now(), null));
        scr.save(new SesionCaja(rodri, pCaja2, LocalDateTime.now(), null));
        scr.save(new SesionCaja(cin, pCaja3, LocalDateTime.now(), null));

        //estados
        pr.save(new Parametro("NUEVO", "ESTADO"));
        pr.save(new Parametro("PROCESADO", "ESTADO"));
        pr.save(new Parametro("OK", "ESTADO"));
        pr.save(new Parametro("OBSERVADO", "ESTADO"));
        //topes
        LocalDateTime fechaAlta = LocalDateTime.now();
        topesRepo.save(new TopeCompra(5000f, 50000f, fechaAlta));
      
  

        //*******Clientes data**********
        Cliente c1 = new Cliente("27444999", "Rodrigo", "Suarez", "rsuarez@cc.com", 38, new Date(), null);
        //Acumulado siempre debo poner el padre en el dueño de la realacion, para que me guarde la relacion
        AcumuladoCliente ac1 = new AcumuladoCliente(c1, Month.MAY, 0, new Float(20),
                new Float(100), pMBase, LocalDateTime.now());
        ac1.setCliente(c1);
        Cliente c2 = new Cliente("22478700", "Tito", "Sixto", "tito@cc.com", 38, new Date(), null);
        Cliente c3 = new Cliente("28478718", "Fernando", "Fresco", "ffresco.com", 38, new Date(), null);
        c1.setAcumulado(ac1);
        //     c2.setAcumulado(ac1);
        //     c3.setAcumulado(ac1);
        cliR.save(c1);
        cliR.save(c2);
        cliR.save(c3);

        /**
         * *****Cotizaciones+++++++++++++++
         */
        Cotizacion cot1 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 27, 0, 0), pMUsd, pEntidad, pTcMin, 20f, 22f,
                0.1f, 0.12f, pIBillete,  pMBase,0.1f);
        Cotizacion cot2 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 26, 0, 0), pMUsd, pEntidad, pTcMin, 9f, 10f,
                0.1f, 0.12f, pIBillete,  pMBase,0.2f);
        Cotizacion cot3 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 25, 0, 0), pMUsd, pEntidad, pTcMin, 8f, 9f,
                0.1f, 0.12f, pIBillete,  pMBase,0.4f);
        Cotizacion cot4 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 3, 0, 0), pMReal, pEntidad, pTcMin, 30f, 32f,
                0.1f, 0.12f, pIBillete,  pMBase,0.4f);
        Cotizacion cot5 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 1, 0, 0), pMReal, pEntidad, pTcMin, 8f, 9f,
                0.1f, 0.12f, pIBillete,  pMBase,0.5f);
        Cotizacion cot6 = new Cotizacion(LocalDateTime.of(2018, Month.FEBRUARY, 1, 0, 0), pMReal, pEntidad, pTcMin, 8f, 9f,
                0.1f, 0.12f, pIBillete,  pMBase,0.10f);
        
        cotS.saveOrUpdate(cot1);
        cotS.saveOrUpdate(cot2);
        cotS.saveOrUpdate(cot3);
        cotS.saveOrUpdate(cot4);
        cotS.saveOrUpdate(cot6);
   
        //*****************Asociar una caja a cada usuario********************/
        dm.intiData();
   
        
        //inicializo los saldos de cajas y tesoro en cero
        for (Parametro caja : dm.getCajas()) {
            for (Parametro moneda : dm.getMonedas()) {
                 for (Parametro inst : dm.getInstrumentos()){
                  acr.save(new AcumuladoCaja(LocalDate.now(),inst, moneda, 0, 0, 0,caja)); 
                }
            }
        }
     

    }
}
