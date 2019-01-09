 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.models;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Saviour Umoeka
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
