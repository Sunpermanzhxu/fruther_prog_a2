package com.dah.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController extends Controller {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField confirmPassField;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private Text loginErrText;

    public RegisterController() {

    }


    
}
