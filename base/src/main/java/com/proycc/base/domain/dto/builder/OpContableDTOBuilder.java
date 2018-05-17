/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.domain.dto.builder;

import com.proycc.base.domain.AcumuladoCaja;
import com.proycc.base.domain.Cliente;
import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.Operacion;
import com.proycc.base.domain.OperacionItem;
import com.proycc.base.domain.Parametro;
import com.proycc.base.domain.Role;
import com.proycc.base.domain.TopeCompra;
import com.proycc.base.domain.dto.OperacionDTO;
import static com.proycc.base.domain.dto.builder.OpDTOBuilder.LOGGER;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 *
 * @author fafre
 */
@Service
public class OpContableDTOBuilder extends OpDTOBuilder {
    
    
    @Override
    protected void buildSpecificHeader(OperacionDTO opDTO, BindingResult result) {
        //en la operaion contable el cliente es null
        opDTO.getOperacion().setCliente(null);

        //Aca la caja es libre se selecciona asi que mando un por defecto
        Parametro caja = dataMaster.getCajas().get(0);
        opDTO.getOperacion().setCaja(caja);
        
        //los acumulados
        opDTO.setAcumCajaO(new AcumuladoCaja());
        opDTO.setAcumCajaD(new AcumuladoCaja());
        



    }

    @Override
    protected void buildSpecificBody(OperacionDTO opDTO, BindingResult result) {
    }

    @Override
    public void setTipoMov(OperacionDTO opDTO) {                
        //pongo el tipo de mov
        opDTO.getOperacion().setTipoMov(OPERACION_CONTABLE);
    }

    @Override
    protected void buidSpecificOpFromDTO(OperacionDTO opDTO) {
        Operacion opAGravar = opDTO.getOperacion();
        
        //parametrizo las suboperacions
        OperacionItem opO = opDTO.getOpO();
        opO.setCaja(paramRepo.findOne(opO.getCaja().getId()));
   
        //parametrizo las suboperacion destino
        OperacionItem opD = opDTO.getOpD();
        opD.setCaja(paramRepo.findOne(opD.getCaja().getId()));
        opD.setMonto(opO.getMonto());
              

    }

}
