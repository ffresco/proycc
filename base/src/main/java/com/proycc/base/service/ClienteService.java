/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service;

import com.proycc.base.domain.Cliente;
import com.proycc.base.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fafre
 */
@Service
public class ClienteService implements BasicService<Cliente>{
    
    private ClienteRepository cliRepo;

    @Autowired
    public ClienteService(ClienteRepository cliRepo) {
        this.cliRepo = cliRepo;
    }

    @Override
    public void delete(Cliente entity) {
        cliRepo.delete(entity);
    }

    @Override
    public Cliente getById(Long id) {
        return cliRepo.findOne(id);
    }

    @Override
    public Cliente saveOrUpdate(Cliente entity) {
       return cliRepo.save(entity);
    }

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) cliRepo.findAll();
    }

    public Cliente findByDocumento(String documento) {
        return cliRepo.findByDocumento(documento);
    }
    
 
    
    
}
