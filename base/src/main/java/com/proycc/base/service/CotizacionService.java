/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.dto.CotizacionSearchDTO;
import com.proycc.base.repository.CotizacionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author fafre
 */
@Service
public class CotizacionService {
    
    private final CotizacionRepository cotizacionRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }
    
    public Cotizacion getById(Long id){
        return cotizacionRepository.findOne(id);
    }
    
    public Cotizacion saveOrUpdate(Cotizacion c){
        return cotizacionRepository.save(c);
    }
    
    public void delete(Cotizacion c){
        cotizacionRepository.delete(c);
    }
    
    public List<Cotizacion> getAll(){
        return (List<Cotizacion>) cotizacionRepository.findAll();
    }
    
    public List<Cotizacion> getAllContaining(CotizacionSearchDTO
            csdto){
        String tipoOp = csdto.getTipoOp()==null? "":csdto.getTipoOp();
        String moneda = csdto.getMoneda()==null? "":csdto.getMoneda();
        String tipoCmb = csdto.getTipoCmb()==null? "" : csdto.getTipoCmb();
        String inst = csdto.getInstrumento()==null? "": csdto.getInstrumento();
       
        System.out.println("--"+ tipoOp+moneda+tipoCmb+inst);
        return (List<Cotizacion>) cotizacionRepository.findByConstrainLike(tipoOp,moneda,tipoCmb,inst, new Sort(Sort.Direction.ASC,"fecha"));
    }
    
    
}
