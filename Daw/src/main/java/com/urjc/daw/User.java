package com.urjc.daw;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @Column(name = "NAME")
    private String name;

    @Column(name="PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name="ROLE")
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

}
