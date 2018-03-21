/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.repository;

import com.proycc.base.domain.Cotizacion;
import java.util.List;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author fafre
 */
public interface CotizacionRepository extends CrudRepository<Cotizacion,Long>{
    
  
    @Query("select c from Cotizacion c where c.tipoOp like ?1% and c.moneda like ?2% "
            + "and c.tipoCambio like ?3% and c.instrumento like ?4% ")
    List<Cotizacion> findByConstrainLike(String tipoOp,String moneda,
            String tipoCmb,String inst, Sort sort);

}
