/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service;

import com.proycc.base.entitys.User;
import com.proycc.base.entitys.dto.UserCreateFormDTO;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author fafre
 */
public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateFormDTO form);

}
