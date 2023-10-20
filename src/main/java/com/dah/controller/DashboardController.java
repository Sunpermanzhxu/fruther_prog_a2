package com.dah.controller;

import javafx.scene.control.Label;

import com.dah.service.UserService;

import javafx.fxml.FXML;

public class DashboardController extends Controller {

    @FXML
    private Label nameText;
    private String user_full_name;

    public DashboardController() {
        user_full_name = "";
    }

    private String getUserFullName() {
        UserService userService = super.passUserService();
        return userService.getUserFullName();
    }
    
}
