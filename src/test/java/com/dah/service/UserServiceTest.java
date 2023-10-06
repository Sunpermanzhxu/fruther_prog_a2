package com.dah.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    public void retriveUserFromDBDataBotExistTest() {
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

        String username = "qwer";
        String password = "zxcv0987";

        try {
            user_service.retriveUserFromDB(username, password);
            fail("not suposses to carry on");
        } catch (Exception e) {
            assertTrue(true);
        }

    }

}
