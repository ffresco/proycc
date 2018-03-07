/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.repository.CotizacionRepository;
import java.util.List;
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
    
    
}
