package com.dah.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Statement;

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

    // @Test
    // public void registerUserSucessfulTest() {

    //     String mock_username = "neverexited";
    //     String mock_password = "1234never";

    //     String mock_f_name = "not";
    //     String mock_l_name = "matter";

    //     String mock_query = "INSERT INTO User (username, password, first_name, last_name) VALUES ('qwer', 'adf', 'qwesasd', 'wssx');";


    //     Statement mock_statement = mock(Statement.class());
    //     try {
    //         when(mock_statement.executeUpdate(mock_query)).thenReturn(1);
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }

    //     DBService mockDbService = mock(DBService.class);

    //     try {
    //         when(mockDbService.getStatement()).thenReturn(mock_statement);
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }

    //     user_service = new UserService(mockDbService);

    //     try {
    //         user_service.registUser(mock_username, mock_password, mock_f_name, mock_l_name);

    //         assertTrue(true);
    //     } catch (IllegalArgumentException | SQLException e) {
    //         fail(e.getMessage());
    //     }
    // }

}
