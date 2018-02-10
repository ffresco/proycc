/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.repository;

import com.proycc.base.entitys.Role;
import com.proycc.base.entitys.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author fafre
 */
@Service
public class UserRepository {
    
    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
        initRepo();
    }

    private void initRepo() {
        users.add(new User(1, "ffresco@gmail.com", "1", Role.ADMIN));
        users.add(new User(22, "mulo@mulo.com", "1", Role.USER));
        
    }

    public User save(User user) {
        long lastid = (users.get(users.size())).getId();
        user.setId(lastid++);
        users.add(user);
        return user;
    }

    public Collection<User> getAll() {
        return users; //To change body of generated methods, choose Tools | Templates.
    }

    public User findOneByEmail(String email) {
        for (User user : users) {
            if(user.getEmail().equals(user)) return user;
        }
        return null;
    }

    public User getById(long id) {
        for (User user : users) {
            if(user.getId()==id) return user;
        }
        return null;
    }
    
    
    
}
