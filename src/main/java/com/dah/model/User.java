package com.dah.model;

public class User {

    private String username;
    private String password;
    private String first_name;
    private String last_name;

    public User(String username, String password, String first_name, String last_name) {

        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    
}
