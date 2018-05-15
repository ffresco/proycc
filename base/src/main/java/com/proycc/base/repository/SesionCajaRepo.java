/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.repository;

import com.proycc.base.domain.SesionCaja;
import com.proycc.base.domain.User;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fafre
 */
public interface SesionCajaRepo  extends CrudRepository<SesionCaja, Long>{
    
    public SesionCaja findTopByUserOrderByInicioSesionDesc(User usuario);
    
}
