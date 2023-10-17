package com.dah.controller;

import com.dah.service.UserService;
import com.dah.utility.DAH_STATE;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoginController extends Controller {
    
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private Label errText;


    public LoginController() {

    }

    /**
     * move to next plage
     * display error message if login failed
     */
    @FXML
    private void handleLogin() {
        UserService userService = super.passUserService();

        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            userService.retriveUserFromDB(username, password);
            errText.setText("Login Sucessed");
            errText.setStyle("-fx-text-fill: #000000;");

            super.switchAppState(DAH_STATE.DASHBOARD);
        } catch (Exception e) {
            errText.setText(e.getMessage());
            errText.setStyle("-fx-text-fill: #FF0000;");
        }
    }


    /**
     * change the state to move to register
     */
    @FXML
    private void handleRegister() {
        // TODO: to be implanted later
        super.switchAppState(DAH_STATE.REGISITER);
    }

}
