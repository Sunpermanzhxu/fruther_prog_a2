package com.dah.model;

public class User {

    private String username;
    private String password;
    private String first_name;
    private String last_name;

    public User() {
        this.username = "";
        this.password = "";
        this.first_name = "";
        this.last_name = "";
    }

    public User(String username, String password, String first_name, String last_name) {

        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    // getters ----------------------------------

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }

    // setters ----------------------------------

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    
}
