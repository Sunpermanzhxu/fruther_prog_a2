package com.dah;

import java.io.IOException;

import com.dah.controller.*;
import com.dah.model.User;
import com.dah.service.DBService;
import com.dah.service.PostService;
import com.dah.service.UserService;
import com.dah.utility.GUIUtility;
import com.dah.utility.DAH_STATE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application  
{
    private Stage primaryStage;

    private static int window_width;
    private static int window_height;

    private static DBService dbService;
    private static UserService userService;
    private static PostService postService;


    // posts are not needed to store this way
    private User stored_user;

    // private Controller controller;
    private DAH_STATE state;

    private static void initiallize() {
        window_width = GUIUtility.WINDOW_WIDTH;
        window_height = GUIUtility.WINDOW_HEIGHT;

        dbService = new DBService();
        userService = new UserService(dbService);
        postService = new PostService(dbService);

        try {
            dbService.connectToDB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public UserService getUserService() {
        return userService;
    }


    public static void main( String[] args )
    {
        initiallize();

        launch(args);
    }


    /**
     * switch DAH_STATE for user expirence
     * where the actual loop is
     * @param new_state
     */
    public void switchState(DAH_STATE new_state) {
        this.state = new_state;

        showPage();
    }


    /**
     * universal show page function
     * @param primaryStage
     */
    public void showPage() {
        
        try {
            String file_path = state.getFileName();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file_path));
            this.primaryStage.setTitle("Data Analysis Hub");

            // Load the FXML file and set it as the root of the scene
            this.primaryStage.setScene(new Scene(loader.load(), window_width, window_height));

            // Get the controller instance from the loader
            Controller controller = loader.getController();
            controller.setApp(this);
            controller.initiallize();

            this.primaryStage.show();
            
        } catch (IOException  e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(User user) {
        stored_user = user;
    }

    public User getUser() {
        return stored_user;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        state = DAH_STATE.LOGIN;
        
        stored_user = new User();

        // from here the journey begains
        showPage();

    }
}
