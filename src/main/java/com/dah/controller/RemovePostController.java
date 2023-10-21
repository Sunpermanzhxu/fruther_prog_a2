package com.dah.controller;

import java.sql.SQLException;
import com.dah.model.User;
import com.dah.service.PostService;
import com.dah.utility.DAH_STATE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RemovePostController extends Controller {
    
    @FXML
    private TextField idField;
    
    @FXML
    private Button cancleRemoveButton;
    @FXML
    private Button confirmRemoveButton;

    @FXML
    private Label RemoveErrText;

    private User loaded_user;

    public RemovePostController() {
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
    
    private void removePost(String id) throws IllegalArgumentException, SQLException, AssertionError {
        // check valid entry
        int int_id = Integer.parseInt(id);
        PostService postService = passPostService();

        postService.removePost(id, loaded_user);

    }

    @FXML
    private void handleConfirm() {
        String id = idField.getText();
        
        try {
            removePost(id);
            RemoveErrText.setText("Post deleted.");
            RemoveErrText.setStyle("-fx-text-fill: #000000;");
        } catch (IllegalArgumentException | SQLException e) {
            RemoveErrText.setText(e.getMessage());
            RemoveErrText.setStyle("-fx-text-fill: #FF0000;");
        }
    }

}
