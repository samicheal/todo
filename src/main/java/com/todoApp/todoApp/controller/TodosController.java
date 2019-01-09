/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.controller;

import com.todoApp.todoApp.models.Todos;
import com.todoApp.todoApp.models.TodosRepository;
import com.todoApp.todoApp.models.User;
import com.todoApp.todoApp.models.UserRepository;
import com.todoApp.todoApp.services.TodosServiceImpl;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller 
public class TodosController {
     
     @Autowired 
     private TodosRepository todosRepo;
     
     @Autowired
     private UserRepository userRepo;
     
     @Autowired
     private TodosServiceImpl todosServiceImpl;
     
     @RequestMapping("/")
     public String index(){
         return"login";
     }
     
      @RequestMapping(value="/login" ,method=RequestMethod.GET)
     public String login(){
         return"login";
     }
     
     @RequestMapping("/todos")
     public String todosHomePage(Principal principal, Todos todos,Model model){
         User user = userRepo.findByEmail(principal.getName());
         model.addAttribute("welcomeMsg","Welcome " + user.getFirstName()+",");
         model.addAttribute("PImage",user);
         model.addAttribute("tod", todosRepo.findByUserId(user.getId()));
         List userTodos = todosRepo.findByUserId(user.getId());
         if(userTodos.isEmpty()){
             model.addAttribute("emptyList",true);
         }else{
             model.addAttribute("listNotEmpty", true);
         }
         
         return"todos";
     }
     
        @RequestMapping(value="/createTodo" , method=RequestMethod.POST)
        public String createTodo(@ModelAttribute(name="todos") @Valid Todos todos,BindingResult errors, Model model,Principal principal){
            
          User user = userRepo.findByEmail(principal.getName());
          
            if(errors.hasErrors())
            {
               model.addAttribute("tod", todosRepo.findByUserId(user.getId()));
                return "todos";
            }
            
             todosServiceImpl.create(new Todos(todos.getItem(),user));
             todos.setItem("");
              model.addAttribute("tod", todosRepo.findByUserId(user.getId()));
                 System.out.println(todosRepo.findByUserId(user.getId()));
				 
              return "redirect:/todos"; 
    }
        
    @RequestMapping(value="/edit{id}", method=RequestMethod.GET)
    public String editTodo(@PathVariable(name="id") int id, Model model, Todos todo){
         Optional<Todos> tos = todosServiceImpl.find(id);
         
         if(tos.isPresent())
          model.addAttribute("edit", tos.get());
         return "editTodos";
    }
     
    @RequestMapping(value="/update" , method=RequestMethod.POST)
    public String updateTodo(@ModelAttribute(name="edit") @Valid Todos edit,BindingResult errors, Model model,Principal principal){
        User user = userRepo.findByEmail(principal.getName());
        if(errors.hasErrors())
        {
            return "editTodos";
        }
        
          todosRepo.save(edit);
          
          edit.setItem("");
          model.addAttribute("todos", new Todos());
          
          model.addAttribute("tod",todosRepo.findByUserId(user.getId()) );
         return "redirect:/todos";  
    }
    
    @RequestMapping(value="/delete{id}", method=RequestMethod.GET)
     public String deleteTodo(@PathVariable(name="id") int id, Model model, Todos todo,Principal principal){
         User user = userRepo.findByEmail(principal.getName());
         //todosServiceImpl.delete(id);
         todosRepo.deleteById(id);
          model.addAttribute("tod", todosRepo.findByUserId(user.getId()));
         return "redirect:/todos";
     }
     
     @RequestMapping(value="/complete{id}", method=RequestMethod.GET)
     public String completed(@PathVariable(name="id") int id){
        
         Optional<Todos> todo = todosServiceImpl.find(id);
         
         if(todo.isPresent()){
             if (todo.get().getCompleted()==0) 
                 todo.get().setCompleted(1);
             else
                 todo.get().setCompleted(0);     
         }
             
         todosRepo.save(todo.get());
         
         return "redirect:/todos";
     }
}
