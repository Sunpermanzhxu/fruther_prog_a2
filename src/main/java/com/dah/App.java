package com.dah;

import java.io.IOException;

import com.dah.controller.*;
import com.dah.service.DBService;
import com.dah.service.UserService;
import com.dah.utility.GUIUtility;
import com.dah.utility.DAH_STATE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application  
{
    // private Stage primaryStage;

    private static int window_width;
    private static int window_height;

    private static DBService dbService;
    private static UserService userService;
    // TODO: add postService

    // private Controller controller;
    private DAH_STATE state;

    private static void initiallize() {
        window_width = GUIUtility.WINDOW_WIDTH;
        window_height = GUIUtility.WINDOW_HEIGHT;

        dbService = new DBService();
        userService = new UserService(dbService);
        // TODO: add postService

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
        // TODO: generate DBService, UserService, PostService
        initiallize();

        // TODO: loade pages and states function to switch pages

        launch(args);
    }

    public void showPage(Stage primaryStage) {
        
        try {
            String file_path = state.getFileName();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file_path));
            primaryStage.setTitle("Data Analysis Hub");

            // Load the FXML file and set it as the root of the scene
            primaryStage.setScene(new Scene(loader.load(), window_width, window_height));

            // Get the controller instance from the loader
            Controller controller = loader.getController();
            controller.setApp(this);

            primaryStage.show();
            
        } catch (IOException  e) {
            System.out.println("io err");
        }
    }

    // TODO: other show function

    @Override
    public void start(Stage primaryStage) throws Exception {
        state = DAH_STATE.LOGIN;

        showPage(primaryStage);

    }
}
