/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.services;

import com.todoApp.todoApp.models.User;
import com.todoApp.todoApp.models.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saviour Umoeka
 */@Service
public class UserServiceImpl implements UserService {
 @Autowired
 private UserRepository userRepo;
    @Override
    public User create(User user) {
        return userRepo.save(user);
    }
    
}
