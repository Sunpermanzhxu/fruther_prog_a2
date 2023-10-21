package com.dah.controller;

import java.sql.SQLException;

import com.dah.model.User;
import com.dah.service.UserService;
import com.dah.utility.DAH_STATE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditProfileController extends Controller{
    
    @FXML
    private TextField newUsernameField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmNewPasswordField;
    @FXML
    private TextField newFirstNameField;
    @FXML
    private TextField newLastNameField;

    @FXML
    private Button cancleEditButton;
    @FXML
    private Button confirmEditButton;

    @FXML
    private Label editErrText;

    private User loaded_user;

    public EditProfileController() {
        loaded_user = new User();
    }

    @Override
    public void initiallise() {
        loaded_user = passUser();
    }

    @FXML
    private void handleCancel() {
        switchAppState(DAH_STATE.DASHBOARD);
    }

    /**
     * check if new_password matches new_c_password
     * @param new_password
     * @param new_c_password
     * @return {@code true | false}
     */
    private Boolean checkPassConfirm(String new_password, String new_c_password) {
        return new_password.equals(new_c_password);
    }

    /**
     * either one have an entry is sufficent to take action
     * @param new_username
     * @param new_password
     * @param new_c_password
     * @param new_f_name
     * @param new_l_rname
     * @return {@code entry} 0 if no entry, > 0 if there are enrtry
     */
    private int checkEntry(String new_username, String new_password, String new_c_password, String new_f_name, String new_l_rname) {
        int entry = 0;
        entry += new_username.length() + new_password.length() * new_c_password.length();

        entry += new_f_name.length() + new_l_rname.length();

        return entry;
    }

    @FXML
    private void handleConfirm() {
        String new_username = newUsernameField.getText();
        String new_password = newPasswordField.getText();
        String new_c_password = confirmNewPasswordField.getText();
        String new_f_name = newFirstNameField.getText();
        String new_l_rname = newLastNameField.getText();

        if (!checkPassConfirm(new_password, new_c_password)) {
            editErrText.setText("Passwords didn't match!");
            editErrText.setStyle("-fx-text-fill: #FF0000;");
        } else if (checkEntry(new_username, new_password, new_c_password, new_f_name, new_l_rname) <= 0) {
            editErrText.setText("Need at leat one entry!");
            editErrText.setStyle("-fx-text-fill: #FF0000;");
        } else {
            UserService userService = passUserService();
            try {
                userService.editProfile(new_username, new_password, new_f_name, new_l_rname, loaded_user);
                editErrText.setText("Change sucessful, login again to see the changes!");
                editErrText.setStyle("-fx-text-fill: #000000;");

                // TODO: auto logout the user
            } catch (IllegalArgumentException | SQLException e) {
                editErrText.setText(e.getMessage());
                editErrText.setStyle("-fx-text-fill: #FF0000;");
            }
        }

    }
    
}
