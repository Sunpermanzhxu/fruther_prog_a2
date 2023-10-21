package com.dah.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import com.dah.model.Post;
import com.dah.model.User;
import com.dah.service.PostService;
import com.dah.utility.DAH_STATE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RetrivePostController extends Controller {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TableView<Post> resultTable;
    @FXML
    private TableColumn<Post, Integer> idColumn;
    @FXML
    private TableColumn<Post, String> contentColumn;
    @FXML
    private TableColumn<Post, String> authorColumn;
    @FXML
    private TableColumn<Post, Integer> likesColumn;
    @FXML
    private TableColumn<Post, Integer> sharesColumn;
    @FXML
    private TableColumn<Post, String> date_timeColumn;

    
    @FXML
    private Button cancleRetirveButton;
    @FXML
    private Button confirmRetirveButton;

    @FXML
    private Label retriveErrText;

    private User loaded_user;

    public RetrivePostController() {
        loaded_user = new User();
    }

    @Override 
    public void initiallise() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Post, Integer>("ID"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("content"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("author"));
        likesColumn.setCellValueFactory(new PropertyValueFactory<Post, Integer>("likes"));
        sharesColumn.setCellValueFactory(new PropertyValueFactory<Post, Integer>("shares"));
        date_timeColumn.setCellValueFactory(new PropertyValueFactory<Post, String>("date_time"));

        loaded_user = passUser();
        resultTable.setEditable(true);

    }
    
    @FXML
    private void handleCancel() {
        switchAppState(DAH_STATE.DASHBOARD);
    }
    
    private void searchPost(String id) throws IllegalArgumentException, ParseException, SQLException {
        // check valid entry
        int int_id = Integer.parseInt(id);

        PostService postService = passPostService();
        postService.retrivePost(id, loaded_user);
    }

    private void populateTable() {
        PostService postService = passPostService();
        ArrayList<Post> posts = postService.getPosts();

        ObservableList<Post> tableData = FXCollections.observableArrayList();
        
        for (Post post : posts) {
            System.out.println(post.getDateTime());
            tableData.add(post);
            // dataTable.getItems().addAll(post);
        }

        resultTable.setItems(tableData);

    }

    @FXML
    private void handleConfirm() {
        String id = idField.getText();

        try {
            searchPost(id);
            populateTable();
        } catch (IllegalArgumentException | ParseException | SQLException e) {
            retriveErrText.setText(e.getMessage());
            retriveErrText.setStyle("-fx-text-fill: #FF0000;");
        }
    }
    
}
