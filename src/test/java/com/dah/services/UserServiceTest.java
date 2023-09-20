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
            user_service.loadUsers("src/test/java/com/dah/test_data/users.csv");
        } catch (Exception e) {

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

}
