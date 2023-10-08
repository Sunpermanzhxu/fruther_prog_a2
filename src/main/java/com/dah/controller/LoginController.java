package com.dah.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField PasswordField;

    @FXML
    private Button button;
    
    @FXML
    private void handleLogin() {
        System.out.println("Hello, World!");;
    }
}
