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

        user_service = new UserService(null);

    }
    

    @Test
    public void retriveUserFromDBSuccessTest() {
        String username = "qwer";

        String result = user_service.
    }

}
