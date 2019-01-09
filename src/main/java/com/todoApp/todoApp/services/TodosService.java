/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.services;

import com.todoApp.todoApp.models.Todos;
import java.util.Optional;

/**
 *
 * @author Saviour Umoeka
 */
public interface TodosService {
    Todos create(Todos todos);
    Todos edit(Todos todos);
    void delete(int id);
    Optional<Todos> find(int id);
}
