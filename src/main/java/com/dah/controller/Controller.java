package com.dah.controller;

import com.dah.App;
import com.dah.service.PostService;
import com.dah.service.UserService;
import com.dah.utility.DAH_STATE;

public class Controller {
    
    private App app;

    public Controller() {
    }

    public void setApp(App app) {
        this.app = app;
    }

    public App appForUse() {
        return app;
    }

    public UserService passUserService() {
        UserService userService = (UserService) app.getServices().get(0);
        return userService;
    }

    public PostService passsPostService() {
        PostService postService = (PostService) app.getServices().get(1);
        return postService;
    }

    protected void switchAppState(DAH_STATE state) {
        app.switchState(state);
    }
    
}
