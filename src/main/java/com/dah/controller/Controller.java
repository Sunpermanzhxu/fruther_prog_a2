package com.dah.controller;

import com.dah.App;
import com.dah.model.User;
import com.dah.service.PostService;
import com.dah.service.UserService;
import com.dah.utility.DAH_STATE;

public class Controller {
    
    private App app;

    private User logged_user;

    public Controller() {
        logged_user = new User();
    }

    /**
     * to be used when needed to carry logged user
     */
    public void initiallise() {

    }

    public void setApp(App app) {
        this.app = app;
    }

    public UserService passUserService() {
        return app.getUserService();
    }

    public PostService passPostService() {
        return app.getPostService();
    }

    public void storeUser() {
        UserService userService = app.getUserService();
        if (userService.getValidity()) {
            logged_user = userService.getUser();
            app.saveUser(logged_user);
        }
    }

    public String getFullName() {
        logged_user = app.getUser();
        return logged_user.getFullName();
    }

    public User passUser() {
        return app.getUser();
    }

    protected void switchAppState(DAH_STATE state) {
        app.switchState(state);
    }
    
}
