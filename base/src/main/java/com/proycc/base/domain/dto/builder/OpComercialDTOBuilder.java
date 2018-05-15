/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain.dto.builder;

import com.proycc.base.domain.Cliente;
import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.Operacion;
import com.proycc.base.domain.OperacionItem;
import com.proycc.base.domain.Parametro;
import com.proycc.base.domain.Role;
import com.proycc.base.domain.TopeCompra;
import com.proycc.base.domain.dto.OperacionDTO;
import static com.proycc.base.domain.dto.builder.OpDTOBuilder.LOGGER;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 *
 * @author fafre
 */
@Service
public class OpComercialDTOBuilder extends OpDTOBuilder {

    @Override
    /**
     * Necesita recibiir un cliente seteado en la operacion DTO
     */
    protected void buildSpecificHeader(OperacionDTO opDTO, BindingResult result) {
        //recupero el cliente
        Cliente cliente = clienteService.findByDocumento(opDTO.getCliente().getDocumento());
        opDTO.getOperacion().setCliente(cliente);
        opDTO.setCliente(cliente);
        LOGGER.info("El cliente recuperado para la op : " + cliente.toString());

        //Recupero la caja del usuario --> Debe salir de una tabla cajas - usuarios, con fecha pedir la fecha mas reciente
        Parametro caja = operacionService.getCaja(opDTO.getOperacion().getUser());
        //agregar validaciones para no usuario
        if (caja == null && opDTO.getOperacion().getUser().getRole().equals(Role.USER)) {
            result.reject("sin.caja.asociada", "el usuario no tiene una caja asociada");
        } else {
            //por ahora asigno una caja por defecto
            caja = dataMaster.getCajas().get(0);
        }
        opDTO.getOperacion().setCaja(caja);

        //seteo los topes del sistema a modo informativo
        TopeCompra tope = dataMaster.getTopes().get(0);
        opDTO.setTope(tope);

    }

    @Override
    protected void buildSpecificBody(OperacionDTO opDTO, BindingResult result) {
    }

    @Override
    public void setTipoMov(OperacionDTO opDTO) {
        //pongo el tipo de mov
        opDTO.getOperacion().setTipoMov("COMERCIAL");
    }

    @Override
    protected void buidSpecificOpFromDTO(OperacionDTO opDTO) {
        Operacion opAGravar = opDTO.getOperacion();
        Parametro caja = paramRepo.findByValor(opAGravar.getCaja().getValor());
        Parametro tipoOp = paramRepo.findByValor(opAGravar.getTipoOp().getValor());
        Parametro tipoCambio = paramRepo.findByValor(opAGravar.getTipoCambio().getValor());
        opAGravar.setCaja(caja);
        opAGravar.setTipoOp(tipoOp);
        opAGravar.setTipoCambio(tipoCambio);
        
        //parametrizo las suboperacions
        OperacionItem opO = opDTO.getOpO();
        opO.setCaja(caja);
   
        //parametrizo las suboperacion destino
        OperacionItem opD = opDTO.getOpD();
        opD.setCaja(caja);
              
    }

}