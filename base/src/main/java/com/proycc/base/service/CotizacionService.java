/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.dto.CotizacionSearchDTO;
import com.proycc.base.repository.CotizacionRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    
       public List<Cotizacion> getAllContaining2(CotizacionSearchDTO
            csdto){
        String tipoOp = csdto.getTipoOp()==null? "":csdto.getTipoOp();
        String moneda = csdto.getMoneda()==null? "":csdto.getMoneda();
        String tipoCmb = csdto.getTipoCmb()==null? "" : csdto.getTipoCmb();
        String inst = csdto.getInstrumento()==null? "": csdto.getInstrumento();
       
        System.out.println("--"+ tipoOp+moneda+tipoCmb+inst);
        Page<Cotizacion> page = cotizacionRepository.getLastCotizacionByMoneda(tipoOp,moneda,tipoCmb,inst, 
               new PageRequest(0,5, new Sort(Sort.Direction.DESC,"fecha")));
        System.out.println("la primer cotizacion " + page.getContent().get(0));
        return (List<Cotizacion>) page.getContent();
    }

    public Cotizacion getDistinctByMoneda(String moneda) {
       return cotizacionRepository.findTopByMonedaOrderByFechaDesc(moneda);
    }
    
    public List<Cotizacion> getLatestCotizaciones(List monedas){
        List<Cotizacion> lastCots = new ArrayList<>();
        for (Iterator iterator = monedas.iterator(); iterator.hasNext();) {
            String next = (String) iterator.next();
            lastCots.add(cotizacionRepository.findTopByMonedaOrderByFechaDesc(next));
        }
        return lastCots;
    }
 
    
    
}
