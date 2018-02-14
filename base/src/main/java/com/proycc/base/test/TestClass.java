/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.test;

import com.proycc.base.domain.Role;
import com.proycc.base.domain.User;
import com.proycc.base.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fafre
 */
public class TestClass {
    
    private static final Logger slf4jLogger = LoggerFactory.getLogger(TestClass.class);
 
    public static void main(String[] args) {
        System.out.println("hello world");
        UserRepository ur = new UserRepository();
        ur.save(new User(4, "22", "ffff", Role.USER));
        System.out.println(ur.getAll());
        
        System.out.println("--Logger example--");
        slf4jLogger.info("mensaje={}","probando el log");
        slf4jLogger.debug("objeto={}",new User());
        slf4jLogger.error("operacion fallo={}", "opcrear", new NoSuchMethodError());

        
    }
}
