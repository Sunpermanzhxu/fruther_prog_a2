package com.dah.controller;

import javafx.scene.control.Label;

import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    private Label nameText;
    private String user_full_name;

    public DashboardController() {
        user_full_name = "";
    }
    
}
