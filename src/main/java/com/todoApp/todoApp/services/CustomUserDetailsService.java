/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.services;

import com.todoApp.todoApp.models.User;
import com.todoApp.todoApp.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
        
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         System.out.println("here now");
         User user = userRepo.findByEmail( email);
         CustomUserDetails userDetails = null;
         
          if(user != null)
         {
             userDetails = new CustomUserDetails();
             // System.out.println("User : " + user.toString());
             userDetails.setUser(user);
         }
         else
             throw new UsernameNotFoundException("User with email add "+ email +" does not exist");
         
         return userDetails;
    }
    
}
