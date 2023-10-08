package com.dah;

import com.dah.controller.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application  
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LoginView.fxml"));
        primaryStage.setTitle("FXML Sample");

        // Load the FXML file and set it as the root of the scene
        primaryStage.setScene(new Scene(loader.load()));

        // Get the controller instance from the loader
        LoginController controller = loader.getController();

        primaryStage.show();
    }
}
