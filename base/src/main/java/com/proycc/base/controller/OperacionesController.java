/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.controller;

import com.proycc.base.domain.Cliente;
import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.DataMaster;
import com.proycc.base.domain.Operacion;
import com.proycc.base.domain.OperacionItem;
import com.proycc.base.domain.Parametro;
import com.proycc.base.domain.User;
import com.proycc.base.domain.dto.OperacionDTO;
import com.proycc.base.domain.enums.Estado;
import com.proycc.base.domain.validator.OperacionProcessValidator;
import com.proycc.base.service.ClienteService;
import com.proycc.base.service.CotizacionService;
import com.proycc.base.service.OperacionService;
import com.proycc.base.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fafre
 */
@Controller
@RequestMapping(value = "/operaciones")
public class OperacionesController implements CrudControllerInterface<Object, OperacionDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperacionesController.class);
    private ClienteService clienteService;
    private OperacionService operacionService;
    private UserService userService;
    private DataMaster dataMaster;
    private CotizacionService cotizacionService;
    private OperacionProcessValidator opProcValidator;

    @Autowired
    public OperacionesController(ClienteService clienteService, OperacionService operacionService,
            UserService userService, CotizacionService cotizacionService, DataMaster dataMaster,
            OperacionProcessValidator opv) {
        this.clienteService = clienteService;
        this.operacionService = operacionService;
        this.userService = userService;
        this.dataMaster = dataMaster;
        this.cotizacionService = cotizacionService;
        this.opProcValidator = opv;
    }

    @Override
    public ModelAndView getMainPage(Object searchDTO, BindingResult bindingResult) {
        return new ModelAndView("operaciones");
    }

    @Override
    public ModelAndView search(Object searchDTO, BindingResult bindingResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelAndView getCreatePage(OperacionDTO objectDTO, BindingResult bindingResult) {
        LOGGER.debug("-------Le peuge a main page------------");

        //recupero el cliente
        Cliente cliente = clienteService.findByDocumento("27444999");
        LOGGER.info("El cliente recuperado para la op : " + cliente.toString());

        //Recupero el usuario
        User usuario = userService.getCurrentUser();
        LOGGER.info("El usuario recuperado para la op es: " + usuario);

        //Recupero la caja del usuario --> Debe salir de una tabla cajas - usuarios, con fecha pedir la fecha mas reciente
        Parametro caja = new Parametro("CAJA-1", "CAJAS");

        //seteo un estado para las nuevas operaciones  -->Debe salir de dataMaster
        Parametro estado = Estado.NUEVO.getEstado();

        //genero la operacion y las Suboperaciones origen y destino -->tipo de operacion, etc todo pasar a parametro
        List<OperacionItem> itemsOp = new ArrayList<>();
        Operacion op = new Operacion(LocalDateTime.now(), cliente, caja,
                null, null, new Cotizacion(),
                estado, "", null);
        itemsOp.add(new OperacionItem(op, null, null, 0f, 0f, "ORIGEN", new Parametro("INGRESO", "M"), 1));
        itemsOp.add(new OperacionItem(op, null, null, 0f, 0f, "DESTINO", new Parametro("EGRESO", "M"), 2));
        op.setOperacionItems(itemsOp);

        //creo el dto
        OperacionDTO opDTO = new OperacionDTO(op, cliente, op.getOpItemO(), op.getOpItemD());
        LOGGER.info("Cree el siguiente dto para operar : " + opDTO);

        //seto los flags de configuracion
        operacionService.configAltaScreen(opDTO);

        //Preparo el moddel and view
        ModelAndView mav = new ModelAndView("operaciones_create");
        mav.addObject("operacionDTO", opDTO);
        return mav;

    }

    @RequestMapping(value = "/save", params = {"modificar"})
    public ModelAndView modif(@ModelAttribute(value = "operacionDTO") OperacionDTO opDTO,
            BindingResult result) {
        LOGGER.debug("----------Le pegue al modificar---------");
        //Seteo nuevamente los flags de modificacion
        operacionService.configAltaScreen(opDTO);

        //Cambio el estado a nuevo
        opDTO.getOperacion().setEstado(Estado.NUEVO.getEstado());

        //guado todo en el mav
        ModelAndView mav = new ModelAndView("operaciones_create");
        mav.addObject("operacionDTO", opDTO);
        return mav;

    }

    @RequestMapping(value = "/save", params = {"procesar"})
    public ModelAndView process(@ModelAttribute(value = "operacionDTO") OperacionDTO opDTO,
            BindingResult result) {
        LOGGER.debug("--------------------Entre al procesar------------------");
        //no necesito controllar estados

        //recupero la operacion
        Operacion op = opDTO.getOperacion();
        LOGGER.info("Esta es el DTO a procesar : " + opDTO);

        //valido la seleccion de combos
        opProcValidator.validate(opDTO, result);
        if (result.hasErrors()) {
            //seto los flags de configuracion de pantalla
            operacionService.configAltaScreen(opDTO);
            ModelAndView mav = new ModelAndView("operaciones_create");
            mav.addObject("operacionDTO", opDTO);
            return mav;
        }

        //Si esta todo ok pido la ultima cotizacion y calculo la operacion
        Cotizacion cot = cotizacionService.getLastCotizacionOpOD(op, opDTO.getOpO(), opDTO.getOpD(),
                new Parametro(dataMaster.getMonedaBase(), ""));
        float montoOp;
        if (cot.isCompra()) {
            //si le compro la mnd ext -> mnd ext * cot.compra
            montoOp = opDTO.getOpO().getMonto() * cot.getCotizacionCmp();
        } else {
            //si le vendo moneda ext es --> monedabase Recibida / cot.vta  
            montoOp = opDTO.getOpO().getMonto() / cot.getCotizacionVta();
        }
        float vuelto = opDTO.getOpO().getMontoRecVlt() - opDTO.getOpO().getMonto();
        opDTO.getOpD().setMonto(montoOp);
        opDTO.getOpD().setMontoRecVlt(vuelto);
        op.setCotizacion(cot);

        //pongo un flag para invalidar el resto de los campos
        operacionService.configProcessScreen(opDTO);

        //Cambio el estado de la Cotizacion a procesado
        op.setEstado(Estado.PROCESADO.getEstado());

        //guado todo en el mav
        ModelAndView mav = new ModelAndView("operaciones_create");
        mav.addObject("operacionDTO", opDTO);
        return mav;
    }

    @Override
    @RequestMapping(value = "/save", params = {"guardar"})
    public ModelAndView save(@ModelAttribute(value = "operacionDTO") OperacionDTO opDTO,
            BindingResult result) {
        LOGGER.info("-----Entre al save de operaciones------");
        System.out.println("Operacion recuperada " + opDTO.getOperacion());
  
        //Le cambio el estado al OK
        opDTO.getOperacion().setEstado(Estado.OK.getEstado());  
  
        //construyo  el objeto a grabar
        Operacion opAGravar = operacionService.buildOperacionFromDTO(opDTO);
        LOGGER.info("Esta es la operacion a grabar " + opAGravar);
 
        
        //Si el estado es observado lo guardo con comentarios
        
        //Guardo la operacion
        Operacion op = operacionService.save(opDTO.getOperacion());
        opDTO.setOperacion(op);
        operacionService.configReadOnlyScreen(opDTO);
        ModelAndView mav = new ModelAndView("operaciones_create");
        mav.addObject("operacionDTO", opDTO);
        return mav;

    }



    @Override
    public ModelAndView delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModelAndView edit(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @ModelAttribute("dataMaster")
    public DataMaster getDataMaster() {
        System.out.println("--Me meti en el data master--");
        return dataMaster;
    }

}
