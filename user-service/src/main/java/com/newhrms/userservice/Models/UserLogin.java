package com.newhrms.userservice.Models;

import javax.persistence.*;

@Entity
@Table(name="userlogin")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="emailId")
    private String emailId;

    public UserLogin() {
    }

    public UserLogin(long userId, String username, String password, String emailId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.emailId = emailId;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
