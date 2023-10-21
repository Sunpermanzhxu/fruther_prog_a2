package com.dah.controller;

import com.dah.utility.DAH_STATE;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class DashboardController extends Controller{

    @FXML
    private Label full_name;

    @FXML
    private Button editProfileButton;
    @FXML
    private Button addAPostButton;

    public DashboardController() {
    }

    @Override
    public void initiallize() {
        setGreetingLable();
    }

    private void setGreetingLable() {
        String name = getFullName();
        full_name.setText(name);
    }

    @FXML
    private void handleEditProfile() {
        switchAppState(DAH_STATE.EDIT_PROFILE);
    }

    @FXML
    private void handleaddAPost() {
        switchAppState(DAH_STATE.ADD_A_POST);
    }
    
}
