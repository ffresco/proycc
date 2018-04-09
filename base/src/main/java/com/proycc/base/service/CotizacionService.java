/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.Operacion;
import com.proycc.base.domain.OperacionItem;
import com.proycc.base.domain.Parametro;
import com.proycc.base.domain.dto.CotizacionSearchDTO;
import com.proycc.base.repository.CotizacionRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
        String tipoOp = getTipoOpParam(csdto);
        String moneda = getMonedaParam(csdto);
        String tipoCmb = getTipoCmbParam(csdto);
        String inst = getTipoInstParam(csdto);
       
        System.out.println("--"+ tipoOp+moneda+tipoCmb+inst);
        return (List<Cotizacion>) cotizacionRepository.findByConstrainLike(tipoOp,moneda,tipoCmb,inst, new Sort(Sort.Direction.ASC,"fecha"));
    }

    private String getTipoInstParam(CotizacionSearchDTO csdto) {
        String inst = csdto.getInstrumento()==null? "": csdto.getInstrumento();
        return inst;
    }

    private String getTipoCmbParam(CotizacionSearchDTO csdto) {
        String tipoCmb = csdto.getTipoCmb()==null? "" : csdto.getTipoCmb();
        return tipoCmb;
    }

    private String getMonedaParam(CotizacionSearchDTO csdto) {
        return csdto.getMoneda()==null? "":csdto.getMoneda();
    }
    
       public List<Cotizacion> getAllContaining2(CotizacionSearchDTO
            csdto){
        String tipoOp = getTipoOpParam(csdto);
        String moneda = getMonedaParam(csdto);
        String tipoCmb = getTipoCmbParam(csdto);
        String inst = getTipoInstParam(csdto);
       
        System.out.println("--"+ tipoOp+moneda+tipoCmb+inst);
        Page<Cotizacion> page = cotizacionRepository.getLastCotizacionByMoneda(tipoOp,moneda,tipoCmb,inst, 
               new PageRequest(0,5, new Sort(Sort.Direction.DESC,"fecha")));
        //System.out.println("la primer cotizacion " + page.getContent().get(0));
        return (List<Cotizacion>) page.getContent();
    }

    private String getTipoOpParam(CotizacionSearchDTO csdto) {
        String tipoOp = csdto.getTipoOp()==null? "":csdto.getTipoOp();
        return tipoOp;
    }
    
    private String getEntidadParam(CotizacionSearchDTO csdto) {
        String entidad = csdto.getEntidad()==null? "EMPRESA":csdto.getEntidad();
        return entidad;
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
 
    public Cotizacion getLastCotizacionOpOD(Operacion op, OperacionItem opO, OperacionItem opD,Parametro monedaBase){
        CotizacionSearchDTO csdto = getCotizacionSearchDTO(op,opO,opD,monedaBase);
        
        String tipoOp = getTipoOpParam(csdto);
        String moneda = getMonedaParam(csdto);
        String tipoCmb = getTipoCmbParam(csdto);
        String inst = getTipoInstParam(csdto);
        String entidad = getEntidadParam(csdto);
        
        System.out.println("Este es csdto " + csdto);
        System.out.println("--"+ tipoOp+moneda+tipoCmb+inst+entidad);
        Cotizacion cot = cotizacionRepository.
                findTopByMonedaAndEntidadAndTipoCambioAndTipoOpAndInstrumentoOrderByFechaDesc(moneda, entidad, tipoCmb, tipoOp, inst);
        //System.out.println("la primer cotizacion " + page.getContent().get(0));
        cot.setCompra(iscompra(opO.getMoneda(), monedaBase));
        System.out.println("Cotizacion buscada y encotrada " + cot);
        return cot;

    }

    private CotizacionSearchDTO getCotizacionSearchDTO(Operacion op, OperacionItem opO, OperacionItem opD, Parametro monedaBase) {
        OperacionItem opAUtilizar = getOperacionAUtilizar(opO, monedaBase, opD);
        CotizacionSearchDTO csdto = new CotizacionSearchDTO();
        csdto.setEntidad("EMPRESA"); //en un futuro ver si hay una cotizacion especial para esta entidad
        System.out.println(" moneda que traigo" + opAUtilizar.getMoneda().getValor());
        csdto.setTipoCmb(op.getTipoCambio().getValor());
        csdto.setTipoOp(op.getTipoOp().getValor());
        csdto.setInstrumento(opAUtilizar.getInstrumento().getValor());
        csdto.setMoneda(opAUtilizar.getMoneda().getValor());
        System.out.println("Este es el csdto que arme "+csdto);
        return csdto;
        }

    private OperacionItem getOperacionAUtilizar(OperacionItem opO, Parametro monedaBase, OperacionItem opD) {
        //busco la moneda extranjera, si el origen es igual a la moneda base, 
        //entonces la extranjera la tiene la op de destino
        if (opO.getMoneda().getValor().equals(monedaBase.getValor())) {
           return opD;
        }else{
           return opO;
        }
        
    }
    /**
     * Devuelve true si la cotizacion a tomar es la de compra
     * Devuelve false si la cotizacion a o tomar es de venta
     * Si lo que se recibe(origen) es Moneda Base --> es una Venta
     * Si lo que se recibe(origen) es Moneda Extranjera --> es una compra
     */
    public boolean iscompra(Parametro monedaO,Parametro monedaB){
        //si la mone
        boolean venta = (monedaO.getValor().equals(monedaB.getValor()));
        return !venta;
    }
    
  
    
    
}
