package com.dah.controller;

import com.dah.App;
import com.dah.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
            // TODO: move to next page
        } catch (Exception e) {
            errText.setText("Username or password is incorect");
            errText.setStyle("-fx-text-fill: #FF0000;");
        }
    }


    @FXML
    private void handleRegister() {
        // TODO: to be implanted later
    }

}
