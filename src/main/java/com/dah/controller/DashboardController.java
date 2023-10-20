package com.dah.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController extends Controller{

    @FXML
    private Label full_name;

    public DashboardController() {
    }

    @Override
    public void initiallize() {
        // full_name.setText(getFullName());
        setGreetingLable();
    }

    private void setGreetingLable() {
        System.out.println("qwer");
        String name = getFullName();
        System.out.println(name);
        full_name.setText(name);
    }
    
}
