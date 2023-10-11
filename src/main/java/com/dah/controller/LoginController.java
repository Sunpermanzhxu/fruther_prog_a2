package com.dah.controller;

import com.dah.App;
import com.dah.service.UserService;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class LoginController extends Controller {
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField PasswordField;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private Text loginErrText;

    private UserService userService;


    public LoginController() {
        // this.userService = super.getUserService();

    }

    /**
     * move to next plage
     * display error message if login failed
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = PasswordField.getText();

        try {
            userService.retriveUserFromDB(username, password);
            // TODO: move to next page
        } catch (Exception e) {
            loginErrText.setText(e.getMessage());
        }
    }


    @FXML
    private void handleRegister() {
        // TODO: to be implanted later
    }

}
