package com.dah.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import com.dah.model.User;


public class UserServiceTest {

    DBService db_service;
    UserService user_service;


    @Before
    public void setUp() {
        db_service = new DBService();
        try {
            db_service.connectToDB();
        } catch (Exception e) {
        }
        user_service = new UserService(db_service);

    }
    

    @Test
    public void retriveUserFromDBSuccessTest() {
        
        // db_service = new DBService();
        // try {
        //     db_service.connectToDB();
        // } catch (Exception e) {
        // }
        // user_service = new UserService(db_service);

        String username = "qwer";
        String password = "1234qwer";

        try {
            user_service.retriveUserFromDB(username, password);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        User result_user = user_service.getUser();

        assertEquals(username, result_user.getUsername());
        assertEquals(password, result_user.getPassword());
    }


    @Test
    public void retriveUserFromDBDataNotExistTest() {
        
        // db_service = new DBService();
        // try {
        //     db_service.connectToDB();
        // } catch (Exception e) {
        // }
        // user_service = new UserService(db_service);

        // also a test for wrong username

        String username = "wasd";
        String password = "zxcvbnm";

        try {
            user_service.retriveUserFromDB(username, password);
            fail("not suposses to carry on");
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void retriveUserFromDBWWrongPasswordTest() {
        
        // db_service = new DBService();
        // try {
        //     db_service.connectToDB();
        // } catch (Exception e) {
        // }
        // user_service = new UserService(db_service);

        String username = "qwer";
        String password = "zxcv0987";

        try {
            user_service.retriveUserFromDB(username, password);
            fail("not suposses to carry on");
        } catch (Exception e) {
            assertTrue(true);
        }

    }


    // // do not uncomment if no error in usage
    // @Test
    // public void registUserSucessTest() {
    //     String test_username = "asdf";
    //     String test_password = "asdf";
    //     String test_first_name = "adsf";
    //     String test_last_name = "asdf";

    //     try {
    //         user_service.registUser(test_username, test_password, test_first_name, test_last_name);
    //         assertTrue(true);
    //     } catch (IllegalArgumentException | SQLException e) {
    //         fail(e.getMessage());
    //     }

    //     String reset_dele_query = "DELETE FROM user WHERE username = 'asdf';";

    //     try {
    //         db_service.runUpdateQuery(reset_dele_query);
    //     } catch (SQLException e) {
    //         // no action needed
    //     }

    // }

    @Test
    public void registUseralreadyExistTest() {
        String test_username = "qwer";
        String test_password = "qwer";
        String test_first_name = "qwer";
        String test_last_name = "qwer";

        try {
            user_service.registUser(test_username, test_password, test_first_name, test_last_name);
            fail("should not continue");
        } catch (IllegalArgumentException | SQLException e) {
            assertTrue(true);
        }

        // String reset_dele_query = "DELETE FROM user WHERE username = 'asdf';";

        // try {
        //     db_service.runUpdateQuery(reset_dele_query);
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

    }

    
    // // do not uncomment if no error in usage
    // @Test
    // public void editProfileSucessTest() {
    //     String target_username = "qwer";
    //     String target_password = "1234qwer";
    //     String target_first_name = "Amy";
    //     String target_last_name = "Clar";
    //     User target_user = new User(target_username, target_password, target_first_name, target_last_name);

    //     String test_username = "asdf";
    //     String test_password = "asdf";
    //     String test_first_name = "adsf";
    //     String test_last_name = "asdf";
    //     User resultUser = new User(test_username, test_password, test_first_name, test_last_name);

    //     try {
    //         user_service.editProfile(test_username, test_password, test_first_name, test_last_name, target_user);
    //         assertTrue(true);
    //     } catch (IllegalArgumentException | SQLException e) {
    //         fail(e.getMessage());
    //     }

    //     // String reset_dele_query = "DELETE FROM user WHERE username = 'asdf';";

    //     // reset
    //     try {
    //         user_service.editProfile(target_username, target_password, target_first_name, target_last_name, resultUser);
    //     } catch (SQLException e) {
    //         // no action needed
    //     }

    // }

    
    // // do not uncomment if no error in usage
    // @Test
    // public void editProfilePartiallyTest() {
    //     String target_username = "qwer";
    //     String target_password = "1234qwer";
    //     String target_first_name = "Amy";
    //     String target_last_name = "Clar";
    //     User target_user = new User(target_username, target_password, target_first_name, target_last_name);

    //     String test_username = "";
    //     String test_password = "asdf";
    //     String test_first_name = "adsf";
    //     String test_last_name = "asdf";
    //     // notice "target_username" instead of test_username
    //     User resultUser = new User(target_username, test_password, test_first_name, test_last_name);

    //     try {
    //         user_service.editProfile(test_username, test_password, test_first_name, test_last_name, target_user);
    //         assertTrue(true);
    //     } catch (IllegalArgumentException | SQLException e) {
    //         fail(e.getMessage());
    //     }

    //     // String reset_dele_query = "DELETE FROM user WHERE username = 'asdf';";

    //     // reset
    //     try {
    //         user_service.editProfile(target_username, target_password, target_first_name, target_last_name, resultUser);
    //     } catch (SQLException e) {
    //         // no action needed
    //     }

    // }

}
