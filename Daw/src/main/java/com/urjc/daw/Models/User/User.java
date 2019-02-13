package com.urjc.daw.Models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;

@Entity// This tells Hibernate to make a table out of this class
@SessionScope
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String userType;


    public Integer getId() {
        return idUser;
    }

    public void setId(Integer id) {
        this.idUser = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpassword() {
        return password;
    }

    public void setPassword(String name) {
        this.password = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public User(String name, String password, String email, String userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }
}
