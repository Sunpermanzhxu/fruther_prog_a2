// ---------------------------------!!! HALT !!!------------------------------------------
// all test regarding to fxml files are not aviliable for security reason
// ---------------------------------------------------------------------------------------

// package com.dah.view;

// import java.io.IOException;

// import org.junit.Test;
// import org.testfx.api.FxRobot;
// import org.testfx.framework.junit.ApplicationTest;

// import com.dah.App;
// import com.dah.controller.Controller;
// import com.dah.utility.DAH_STATE;

// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import static org.testfx.api.FxAssert.verifyThat;
// import static org.testfx.matcher.base.NodeMatchers.hasText;

// /**
//  * all tests in this file will be about event handeling and page transfer
//  * as other aspects such data checking is handled elsewhere
//  */
// public class LoginTest extends ApplicationTest {

//     private DAH_STATE state;

//     @Override
//     public void start(Stage stage) throws IOException {
//         state = DAH_STATE.LOGIN;

//         try {
//             String file_path = state.getFileName();
//             FXMLLoader loader = new FXMLLoader(getClass().getResource(file_path));
//             stage.setTitle("Data Analysis Hub");

//             // Load the FXML file and set it as the root of the scene
//             stage.setScene(new Scene(loader.load()));

//             // Get the controller instance from the loader
//             Controller controller = loader.getController();
//             controller.setApp(this);

//             stage.show();
            
//         } catch (IOException  e) {
//             System.out.println(e.getMessage());
//         }
//     }



//     @Test
//     public void loginSucessTest(FxRobot robot) {
//         robot.clickOn("#usernameField").write("qwer");
//         robot.clickOn("#passwordField").write("1234qwer");
//         robot.clickOn("#loginButton");
        
//         verifyThat("#loginErrText", hasText("Login Sucessed"));
//     }
    
// }
