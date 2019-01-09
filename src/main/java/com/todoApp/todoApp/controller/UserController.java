/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.controller;

import com.todoApp.todoApp.models.Role;
import com.todoApp.todoApp.models.RoleRepository;
import com.todoApp.todoApp.models.User;
import com.todoApp.todoApp.models.UserRepository;
import com.todoApp.todoApp.services.UserServiceImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author snowcoder
 */@Controller
public class UserController {
	
     public static String uploadDirectory = "src\\main\\resources\\static\\images";
	  
     @Autowired
     private UserServiceImpl userServiceImpl;
     
    @Autowired
    private BCryptPasswordEncoder bcrypt;
       
    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
     private UserRepository userRepo;
       
     @RequestMapping("/register")
     public String registerPage(User user,Model model){
         return "register";
     }
     
     @RequestMapping(value= "/addUser",method=RequestMethod.POST)
     public String addUser(@ModelAttribute(name="user") @Valid User user,BindingResult errors, Model model,
            @RequestParam("image") MultipartFile[] files,@RequestParam("confirmPassword") String confirmPassword)
    { 
         User currentUser = userRepo.findByEmail(user.getEmail());
         boolean addedUser = false;
         
         if(errors.hasErrors()){
         return"register";
         }
         else if(!user.getPassword().equals(confirmPassword)){
                model.addAttribute("passwordMismatch",true);
                return"register";
            }
            
         else if(currentUser!= null){
                model.addAttribute("userExist",true);
                return"register";
            } else{
            
           /* if(!dateOfBirth.matches(dateRegex)){
                model.addAttribute("wrongDate",true);
                return"register";
            }*/
         
              //ecncrypt password and save
        String pwd = user.getPassword();
        String encoded = bcrypt.encode(pwd);
        user.setPassword(encoded);
        
       // StringBuilder fileNames = new StringBuilder(); 
         for (MultipartFile file:files){
             user.setProfileImage(file.getOriginalFilename());
             System.out.println(file.getOriginalFilename());
             Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
             //fileNames.append(file.getOriginalFilename()+" ");
             //model.addAttribute("image",file.getOriginalFilename());
             try {
                 Files.write(fileNameAndPath, file.getBytes());
             } catch (IOException ex) {
                 Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
             }
           
           //System.out.println(file.getOriginalFilename());  
         }
          
         user.setRole(roleRepo.findByRole("user"));
             userServiceImpl.create(new User(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhoneNumber()
                     ,user.getDateOfBirth(),user.getProfileImage(),user.getPassword(),user.getRole()));
             addedUser = true;
             
             if(addedUser == true){
         model.addAttribute("userAdded",addedUser);
             }
         }
     return "login";
     }
     
}
