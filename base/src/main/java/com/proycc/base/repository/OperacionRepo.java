/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.repository;
import com.proycc.base.domain.Operacion;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fafre
 */
public interface OperacionRepo extends CrudRepository<Operacion,Long> {
    
}
