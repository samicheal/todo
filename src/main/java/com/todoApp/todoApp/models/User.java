 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoApp.todoApp.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * 
 */
@Entity
public class User extends AuditModel implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @NotNull
    @NotEmpty(message="Name cannot be Empty")
    @Pattern(regexp="^([a-zA-Z]{2,})$", message="Name must be more than 2 letter and must not contain number")
    private String firstName;

    @NotNull
    @NotEmpty(message="Name cannot be Empty")
    @Pattern(regexp="^([a-zA-Z]{2,})$", message="Name must be more than 2 letter and must not contain number")
    private String lastName;

    @Email
    @NotNull
    @NotEmpty(message="Email cannot be Empty")
    private String email;
    
     @NotEmpty(message="Phone Number cannot be left Empty")
     @Pattern(regexp="([+][2][3][4][1-9]{1}[0-9]{9}|[\\d]{11})", message="Enter valid Phone number")
     @NotNull
    private String phoneNumber;
     
    @NotNull(message="Date of Birth cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
    //@NotNull(message="Please Upload a Profile Image")
    private String profileImage;

    @Size(min = 4)
    @NotNull(message="Please Set up Password")
    private String password;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns= @JoinColumn(name="user_id") , inverseJoinColumns = @JoinColumn(name="role_id"))
    private Role role;
    
    @OneToMany(mappedBy="user")
    private Set<Todos> todos;

    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth, String profileImage, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.profileImage = profileImage;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", profileImage=" + profileImage + ", password=" + password + ", role=" + role + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }

   

   
}
