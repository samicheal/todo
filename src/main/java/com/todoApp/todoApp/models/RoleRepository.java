/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.models;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Saviour Umoeka
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {

    public Role findByRole(String name);
    
}
