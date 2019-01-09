/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.services;

import com.todoApp.todoApp.models.Todos;
import com.todoApp.todoApp.models.TodosRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saviour Umoeka
 */@Service
public class TodosServiceImpl implements TodosService {
    @Autowired
    private TodosRepository todosRepo;
    

    @Override
    public Todos create(Todos todos) {
        return todosRepo.save(todos);
    }

    @Override
    public Todos edit(Todos todos) {
        return todosRepo.save(todos);
    }

    @Override
    public void delete(int id) {
        todosRepo.deleteById(id);
    }

    @Override
    public Optional<Todos> find(int id) {
        return todosRepo.findById(id);
    }
    
}
