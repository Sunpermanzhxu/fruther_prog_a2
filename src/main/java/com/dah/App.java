package com.dah;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dah.controller.*;
import com.dah.model.User;
import com.dah.service.DBService;
import com.dah.service.PostService;
import com.dah.service.Service;
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
    // private static UserService userService;
    // private static PostService postService;
    private static ArrayList<Service> services;

    // private Controller controller;
    private DAH_STATE state;

    private static User logged_user;

    // a bad way of passing user

    private static void initiallize() {
        window_width = GUIUtility.WINDOW_WIDTH;
        window_height = GUIUtility.WINDOW_HEIGHT;

        dbService = new DBService();
        Service userService = new UserService(dbService);
        Service postService = new PostService(dbService);
        services = new ArrayList<Service>(2);
        services.add(userService);
        services.add(postService);

        logged_user = new User();

        try {
            dbService.connectToDB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setLoggedUser(User user) {
        System.out.println("setLoggedUser?");
        logged_user = user;
        System.out.println(logged_user.getUserFullName());
    }

    public User getLoggedUser() {
        System.out.println("getLoggedUser?");
        return logged_user;
    }

    public static void main( String[] args )
    {
        // TODO: generate DBService, UserService, PostService
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

            this.primaryStage.show();
            
            // Get the controller instance from the loader
            Controller controller = loader.getController();
            controller.setApp(this);

            
        } catch (IOException  e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        state = DAH_STATE.LOGIN;

        // from here the journey begains
        showPage();

    }
}
