package com.dah.controller;

import com.dah.App;
import com.dah.model.User;
import com.dah.service.UserService;
import com.dah.utility.DAH_STATE;

public class Controller {
    
    private App app;

    private User logged_user;

    public Controller() {
        logged_user = new User();
    }

    public void initiallize() {

    }

    public void setApp(App app) {
        this.app = app;
    }

    public UserService passUserService() {
        return app.getUserService();
    }

    public void storeUser() {
        UserService userService = app.getUserService();
        if (userService.getValidity()) {
            logged_user = userService.getUser();
            app.saveUser(logged_user);
            System.out.println("++" + logged_user.getFullName() + "++");
        }
    }

    public String getFullName() {
        logged_user = app.getUser();
        System.out.println("++" + logged_user.getFullName() + "++");
        return logged_user.getFullName();
    }

    protected void switchAppState(DAH_STATE state) {
        app.switchState(state);
    }
    
}
