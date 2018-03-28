/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.repository;

import com.proycc.base.domain.Cliente;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author fafre
 */
@Service
public class ClienteRepository {

    private List<Cliente> clientes;

    public ClienteRepository() {
        clientes = new ArrayList<>();
     }


    public void addCli(Cliente cli) {
        Cliente cliGet = getByDocument(cli.getDocumento());
        if (cliGet!=null) {
            clientes.remove(cliGet);
        }
        clientes.add(cli);
    }

    public void removeCli(Cliente cli) {
        clientes.remove(cli);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente getById(String documento) {
        Cliente cli = getByDocument(documento);
        if (cli !=null) {
            return cli;
        }
        return new Cliente();
    }

    private Cliente getByDocument(String documento) {
        for (Cliente next : clientes) {
            if (next.getDocumento().equals(documento)) {
                return next;
            }
        }
        return null;
    }

}
