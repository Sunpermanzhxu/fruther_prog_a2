package com.dah.controller;

import com.dah.App;
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
    private String username;
    @FXML
    private PasswordField passwordField;
    private String password;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private Label loginErrText;


    public LoginController() {
        username = "";
        password = "";
    }

    /**
     * move to next plage
     * display error message if login failed
     */
    @FXML
    private void handleLogin() {
        UserService userService = super.passUserService();

        username = usernameField.getText();
        password = passwordField.getText();

        try {
            userService.retriveUserFromDB(username, password);
            loginErrText.setText("Login Sucessed");
            loginErrText.setStyle("-fx-text-fill: #000000;");
            
            App app = super.appForUse();
            app.setLoggedUser(userService.getUser());

            super.switchAppState(DAH_STATE.DASHBOARD);
        } catch (Exception e) {
            loginErrText.setText(e.getMessage());
            loginErrText.setStyle("-fx-text-fill: #FF0000;");
        }
    }


    /**
     * change the state to move to register
     */
    @FXML
    private void handleRegister() {
        super.switchAppState(DAH_STATE.REGISITER);
    }

}
