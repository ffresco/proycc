/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proycc.base.service;

import com.proycc.base.domain.User;
import com.proycc.base.domain.dto.UserCreateFormDTO;
import com.proycc.base.repository.UserRepository;
import com.proycc.base.service.UserService;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author fafre
 */
@Service
public class UserServiceMemoImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceMemoImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.getById(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.of(userRepository.findOneByEmail(email));

    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public User create(UserCreateFormDTO form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(form.getPassword());
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        String userName = userDetails.getUsername();
        return userRepository.findOneByEmail(userName);
    }

}
