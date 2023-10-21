package com.dah.controller;

import java.sql.SQLException;
import java.text.ParseException;

import com.dah.model.Post;
import com.dah.model.User;
import com.dah.service.PostService;
import com.dah.utility.DAH_STATE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddAPostController extends Controller {

    @FXML
    private TextField idField;
    @FXML
    private TextArea contentTextArea;
    @FXML
    private TextField AuthorField;
    @FXML
    private TextField likesField;
    @FXML
    private TextField sharesField;

    @FXML
    private TextField dayField;
    @FXML
    private TextField monField;
    @FXML
    private TextField yerField;
    @FXML
    private TextField horField;
    @FXML
    private TextField minField;

    
    @FXML
    private Button cancleEditButton;
    @FXML
    private Button confirmEditButton;

    @FXML
    private Label addErrText;

    
    private User loaded_user;

    public AddAPostController() {
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
    
    private void checkInput(String ID, String content, String author, String likes, String shares, String date_time) throws IllegalArgumentException, ParseException, NumberFormatException  {
        
        int int_ID = Integer.parseInt(ID);
        int int_likes = Integer.parseInt(likes);
        int int_shares = Integer.parseInt(shares);

        Post place_hold = new Post(int_ID, content, author, int_likes, int_shares, date_time);
    }
    
    @FXML
    private void handleConfirm() {
        String ID = idField.getText();
        String content = contentTextArea.getText();
        String author = AuthorField.getText();
        String likes = likesField.getText();
        String shares = sharesField.getText();
        String date_time = "";

        String day = dayField.getText();
        String mon = monField.getText();
        if (mon.length() < 2) {
            mon = "0" + mon;
        }
        String yer = yerField.getText();
        String hor = horField.getText();
        String min = minField.getText();
        date_time = day + "/" + mon + "/" + yer + " " + hor + ":" + min;

        try {
            checkInput(ID, content, author, likes, shares, date_time);

            PostService postService = passPostService();

            postService.add_a_post(ID, content, author, likes, shares, date_time, loaded_user);
            addErrText.setText("Post added");
            addErrText.setStyle("-fx-text-fill: #000000;");

        } catch (IllegalArgumentException | ParseException e) {
            addErrText.setText(e.getMessage());
            addErrText.setStyle("-fx-text-fill: #FF0000;");
        } catch (SQLException e) {
            addErrText.setText(e.getMessage());
            addErrText.setStyle("-fx-text-fill: #FF0000;");
        } catch (AssertionError e) {
            addErrText.setText(e.getMessage());
            addErrText.setStyle("-fx-text-fill: #FF0000;");
        }

    }

    
}
