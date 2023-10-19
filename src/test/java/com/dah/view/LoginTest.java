// package com.dah.view;

// import java.io.IOException;

// import org.junit.Test;
// import org.testfx.api.FxRobot;
// import org.testfx.framework.junit.ApplicationTest;

// import com.dah.App;
// import javafx.stage.Stage;

// import static org.testfx.api.FxAssert.verifyThat;
// import static org.testfx.matcher.base.NodeMatchers.hasText;

// /**
//  * all tests in this file will be about event handeling and page transfer
//  * as other aspects such data checking is hadel elsewhere
//  */
// public class LoginTest extends ApplicationTest {

//     @Override
//     public void start(Stage stage) throws IOException {
//         App app = new App();
        
//         app.start(stage);
//     }



//     @Test
//     public void loginSucessTest(FxRobot robot) {
//         robot.clickOn("#usernameField").write("qwer");
//         robot.clickOn("#passwordField").write("1234qwer");
//         robot.clickOn("#loginButton");

//         verifyThat("#loginErrText", hasText("Login Sucessed"));
//     }
    
// }
