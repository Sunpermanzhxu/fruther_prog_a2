package com.dah.service;

import static org.junit.Assert.*;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class DBServicesTest {
    
    DBService db_service;
    
    @Before
    public void setUp() {
        db_service = new DBService();

    }

    @Test
    public void connectToDBSucessTest() {

        try {
            db_service.connectToDB();
        } catch (Exception e) {
            fail("fail loading");
        }

        assertTrue(db_service.checkConnection());

        try {
            db_service.closeConnection();
        } catch (SQLException e) {
            // no actin required
        }

    }

    @Test
    public void closeConnectionSucessTest() {

        try {
            db_service.connectToDB();
        } catch (Exception e) {
            // no test for this is needed
        }

        // check there is connection
        assertTrue(db_service.checkConnection());

        try {
            db_service.closeConnection();

            // the actual test
            assertFalse(db_service.checkConnection());
        } catch (SQLException e) {
            // no actin required
        }

    }

    @Test
    public void closeConnectionNoConnectTest() {

        try {
            db_service.closeConnection();
            assertTrue(true);
        } catch (SQLException e) {
            // no action is needed
        }

    }


    @Test
    public void runSelectQuerySucessTest() {
        String test_username = "mocko";
        String test_password = "qwer1234";
        String test_first_name = "Iam";
        String test_last_name = "Mock";

        try {
            db_service.connectToDB();

            String query = "SELECT * FROM User WHERE username = 'mocko' AND password = 'qwer1234';";
            ResultSet resultSet = db_service.runSelectQuery(query);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");

                assertEquals(test_username, username);
                assertEquals(test_password, password);
                assertEquals(test_first_name, first_name);
                assertEquals(test_last_name, last_name);
            }

        } catch (Exception e) {
            
            fail(e.getMessage());
        }
    }
    

    @Test
    public void runSelectQuerySelectNoneExitTest() {
        String test_username = "mocko";
        String test_password = "qwer1234";
        String test_first_name = "Iam";
        String test_last_name = "Mock";

        try {
            db_service.connectToDB();

            String query = "SELECT * FROM User WHERE username = 'wasd' AND password = 'qwer1234';";
            ResultSet resultSet = db_service.runSelectQuery(query);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");

                assertEquals("", username);
                assertEquals("", password);
                assertEquals("", first_name);
                assertEquals("", last_name);
            }

        } catch (Exception e) {
            
            fail(e.getMessage());
        }
    }


    @Test
    public void runSelectQuerySelectWrongTableTest() {
        String test_username = "mocko";
        String test_password = "qwer1234";
        String test_first_name = "Iam";
        String test_last_name = "Mock";

        try {
            db_service.connectToDB();

            String query = "SELECT * FROM Post WHERE username = 'mocko' AND password = 'qwer1234';";
            ResultSet resultSet = db_service.runSelectQuery(query);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");

                assertFalse(test_username.equals(username));
                assertFalse(test_password.equals(password));
                assertFalse(test_first_name.equals(first_name));
                assertFalse(test_last_name.equals(last_name));
            }

        } catch (Exception e) {
            
            assertTrue(true);
        }
    }

    @Test
    public void runSelectQuerySelectNotExistingTableTest() {
        String test_username = "mocko";
        String test_password = "qwer1234";
        String test_first_name = "Iam";
        String test_last_name = "Mock";

        try {
            db_service.connectToDB();

            String query = "SELECT * FROM Cake WHERE username = 'mocko' AND password = 'qwer1234';";
            ResultSet resultSet = db_service.runSelectQuery(query);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");

                fail("should not continue");
            }

        } catch (Exception e) {
            
            assertTrue(true);
        }
    }


    @Test
    public void runUpdateQuerySucessInsertTest() {
        try {
            db_service.connectToDB();

            String query = "INSERT INTO User (username, password, first_name, last_name) VALUES ('asdf', 'asdf', 'asdf', 'asdf');";
            int rows_changed = db_service.runUpdateQuery(query);
            
            assertTrue(rows_changed >= 1);

            String delete_added_data = "DELETE FROM User WHERE username = 'asdf';";
            db_service.runUpdateQuery(delete_added_data);

        } catch (Exception e) {
            
            fail(e.getMessage());
        }
    }

    
    @Test
    public void runUpdateQueryInsertDupePKTest() {
        try {
            db_service.connectToDB();

            String query = "INSERT INTO User (username, password, first_name, last_name) VALUES ('qwer', 'asdf', 'asdf', 'asdf');";
            int rows_changed = db_service.runUpdateQuery(query);
            
            assertTrue(rows_changed < 1);

            // String delete_added_data = "DELETE FROM User WHERE username = 'asdf';";
            // db_service.runUpdateQuery(delete_added_data);

        } catch (Exception e) {
            assertTrue(true);
        }
        
    }

    
    @Test
    public void runUpdateQueryInsertIntoWrongTableTest() {
        try {
            db_service.connectToDB();

            String query = "INSERT INTO Post (username, password, first_name, last_name) VALUES ('qwer', 'asdf', 'asdf', 'asdf');";
            int rows_changed = db_service.runUpdateQuery(query);
            
            assertTrue(rows_changed < 1);

            // String delete_added_data = "DELETE FROM User WHERE username = 'asdf';";
            // db_service.runUpdateQuery(delete_added_data);

        } catch (Exception e) {
            
            assertTrue(true);
        }
    }

}
