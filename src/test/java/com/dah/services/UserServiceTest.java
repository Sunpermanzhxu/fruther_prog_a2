package com.dah.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.dah.model.User;
import com.dah.utility.FileUtillity;

public class UserServiceTest {

    UserService user_service;

    @Before
    public void setUp() {

        user_service = new UserService();

    }
    
    @Test
    public void loadUserSucessTest() {

        try {
            user_service.loadUsers(FileUtillity.TEST_USER_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(1 == user_service.getUserNum());

    }

    @Test
    public void loadUserWrongFileTest() {

        try {
            user_service.loadUsers("nowhere.csv");

            fail("the data is sucessfully loaded in a not desied way");
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void findUserIndexSucessTest() {

        try {
            // user_service.loadUsers(FileUtillity.TEST_USER_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String username_demanded = "mocko";

        int expected_index = 0;
        
        int function_result = user_service.findUserIndex(username_demanded);

        assertEquals(expected_index, function_result);

    }


    @Test
    public void findUserIndexWrongEntryTest() {

        try {
            user_service.loadUsers(FileUtillity.TEST_USER_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String username_demanded = "qwer";

        int expected_index = -1;
        
        int function_result = user_service.findUserIndex(username_demanded);

        assertEquals(expected_index, function_result);

    }


    @Test
    public void findUserIndexEmptyListTest() {

        String username_demanded = "qwer";

        int expected_index = -1;
        
        int function_result = user_service.findUserIndex(username_demanded);

        assertEquals(expected_index, function_result);

    }

}
