/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.models;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Snowcoder
 */
public interface TodosRepository extends JpaRepository<Todos,Integer> {
    List<Todos> findByUserId(Long UserId);
}
