package com.urjc.daw.Models.User;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.urjc.daw.Models.Answer.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity// This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String userType;
    @ManyToOne
    private Answer idAnswer;


    public long getId() {
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
        this.password = new BCryptPasswordEncoder().encode(password);
        //this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public User(){}

    public void addAnswer(Answer answer){
        this.idAnswer = idAnswer;
    }

}
