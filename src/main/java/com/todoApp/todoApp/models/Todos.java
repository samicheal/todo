/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.models;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author snowcoder
 */@Entity
public class Todos extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
   @NotEmpty
   @NotNull
   @Size(min=3, max=200, message="title cannot be left empty")
    private String item;
    
    @NotNull
    private int completed;
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    
    public Todos() {
    }

    public Todos(String item, User user) {
        this.user=user;
        this.item = item;
    }
    
    public Todos(int id, String item,User user) {
        this.id = id;
        this.item = item;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public int getCompleted() {
        return completed;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    @Override
    public String toString() {
        return "Todos{" + "id=" + id + ", item=" + item + ", completed=" + completed + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Todos other = (Todos) obj;
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }
    
    
}
