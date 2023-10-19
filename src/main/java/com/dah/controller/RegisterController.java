package com.dah.controller;

import java.sql.SQLException;

import com.dah.service.UserService;
import com.dah.utility.DAH_STATE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private Label registErrText;

    public RegisterController() {
    }


    // private boolean checkExitedAccount(String username, String password) {
    //     UserService userService = super.passUserService();

    //     boolean already_exit = false;

    //     try {
    //         userService.retriveUserFromDB(username, "");
    //     } catch (Exception e) {
    //         // need to do nothing
    //     }

    //     already_exit = userService.getValidity();
    //     System.out.println(already_exit);
    //     return already_exit;
    // }

    
    @FXML
    private void handleRegister() {
        UserService userService = super.passUserService();

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confrim_password = confirmPassField.getText();

        String first_name = firstNameField.getText();
        String last_name = lastNameField.getText();

        int total_entry_size = 1;
        total_entry_size *= username.length() * password.length() * confrim_password.length();
        total_entry_size *= first_name.length() * last_name.length();

        if (total_entry_size == 0) {
            // user missed an entry
            registErrText.setText("No empty field is allowed!");
            registErrText.setStyle("-fx-text-fill: #FF0000;");
        } else if (!password.equals(confrim_password)) {
            // user enter differen password
            registErrText.setText("Passwords didn't match!");
            registErrText.setStyle("-fx-text-fill: #FF0000;");
        } else {
            // try to save user
            try {
                userService.registUser(username, password, first_name, last_name);
                registErrText.setText("You have beeb registered!");
                registErrText.setStyle("-fx-text-fill: #000000;");
            } catch (IllegalArgumentException | SQLException e) {
                // handel case when there is already a user with same username
                registErrText.setText("Username unaviliable");
                registErrText.setStyle("-fx-text-fill: #FF0000;");
            }
        }

    }

    @FXML
    private void handleLogin() {
        super.switchAppState(DAH_STATE.LOGIN);
    }
    
}
