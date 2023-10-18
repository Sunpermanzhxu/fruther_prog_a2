package com.dah.controller;

import com.dah.service.UserService;
import com.dah.utility.DAH_STATE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController extends Controller {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPassField;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private Text registErrText;

    public RegisterController() {

    }


    private boolean checkExitedAccount(String username, String password) {
        UserService userService = super.passUserService();

        boolean already_exit = false;

        try {
            userService.retriveUserFromDB(username, password);
        } catch (Exception e) {
            // need to do nothing
        }

        already_exit = userService.getValidity();

        return already_exit;
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean user_existed = checkExitedAccount(username, password);

        if (user_existed) {
            String err_message = "Username unaviliable.";
            registErrText.setText(err_message);
            registErrText.setStyle("-fx-text-fill: #FF0000;");
        } else {
            String err_message = "Account created.";
            registErrText.setText(err_message);
            registErrText.setStyle("-fx-text-fill: #000000;");

            super.switchAppState(DAH_STATE.LOGIN);
        }

    }

    @FXML
    private void handleLogin() {
        super.switchAppState(DAH_STATE.LOGIN);
    }
    
}
