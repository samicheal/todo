/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Saviour Umoeka
 */@Component
public class DevBootStrap implements ApplicationListener <ContextRefreshedEvent>{

    @Autowired
    private RoleRepository roleRepo;
        
        public void createRoles(){
        Role user = new Role("User");
        roleRepo.save(user);    
        }
     
    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
      // createRoles();
    }
    
}
