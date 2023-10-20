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

    public UserService passUserService() {
        return app.getUserService();
    }

    public PostService passsPostService() {
        return app.getPostService();
    }

    protected void switchAppState(DAH_STATE state) {
        app.switchState(state);
    }
    
}
