package com.dah.controller;

import javafx.scene.control.Label;

import com.dah.App;
import com.dah.model.User;
import com.dah.service.UserService;

import javafx.fxml.FXML;

/**
 * at this point forward, there must already have a valid user in userService
 */
public class DashboardController extends Controller {

    @FXML
    private Label nameText;
    private String user_full_name;

    public DashboardController() {
        user_full_name = "";
        
    }

    public void initialize() {
        user_full_name = getUserFullName();
        nameText.setText(user_full_name);
        nameText.setStyle("-fx-text-fill: #000000;");
    }

    private String getUserFullName() {
        App app = super.appForUse();
        System.out.println("here");
        User logged_user = app.getLoggedUser();
        
        return logged_user.getUserFullName();
    }
    
}
