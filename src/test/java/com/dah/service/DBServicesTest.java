// ----------------------------------!!! Halt !!!---------------------------------------------
// some of the tests will affext data integrety of db
// those tests are commented out
// only uncomment and use when there are errors in usage
// -------------------------------------------------------------------------------------------

package com.dah.service;

import static org.junit.Assert.*;

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


    // // do not uncomment if no error in usage
    // @Test
    // public void runUpdateQuerySucessInsertTest() {
    //     try {
    //         db_service.connectToDB();

    //         String query = "INSERT INTO User (username, password, first_name, last_name) VALUES ('asdf', 'asdf', 'asdf', 'asdf');";
    //         int rows_changed = db_service.runUpdateQuery(query);
            
    //         assertTrue(rows_changed >= 1);

    //         String delete_added_data = "DELETE FROM User WHERE username = 'asdf';";
    //         db_service.runUpdateQuery(delete_added_data);

    //     } catch (Exception e) {
            
    //         fail(e.getMessage());
    //     }
    // }

    
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


    // // do not uncomment if no error in usage
    // @Test
    // public void runUpdateQueryUpdateTableSucessTest() {
    //     try {
    //         db_service.connectToDB();

    //         String query = "UPDATE User SET username = 'asdf', password = 'asdf', first_name = 'asdf', last_name = 'asdf' WHERE username = 'qwer';";
    //         int rows_changed = db_service.runUpdateQuery(query);
            
    //         assertTrue(rows_changed >= 1);

    //         String reset_update_data = "UPDATE User SET username = 'qwer', password = 'qwer', first_name = 'qwer', last_name = 'qwer' WHERE username = 'asdf';";
    //         db_service.runUpdateQuery(reset_update_data);

    //     } catch (Exception e) {
            
    //         fail(e.getMessage());
    //     }
    // }

    
    // // do not uncomment if no error in usage
    // @Test
    // public void runUpdateQueryUpdateWithSameDataSuccessTest() {
    //     try {
    //         db_service.connectToDB();

    //         String query = "UPDATE User SET username = 'qwer', password = 'asdf', first_name = 'asdf', last_name = 'asdf' WHERE username = 'qwer';";
    //         int rows_changed = db_service.runUpdateQuery(query);
            
    //         assertTrue(rows_changed >= 1);

    //         String reset_update_data = "UPDATE User SET username = 'qwer', password = 'qwer', first_name = 'qwer', last_name = 'qwer' WHERE username = 'qwer';";
    //         db_service.runUpdateQuery(reset_update_data);

    //     } catch (Exception e) {
            
    //         fail(e.getMessage());
    //     }
    // }

    
    @Test
    public void runUpdateQueryUpdateValidPKConstrainTest() {
        try {
            db_service.connectToDB();

            String query = "UPDATE User SET username = 'mocko', password = 'asdf', first_name = 'asdf', last_name = 'asdf' WHERE username = 'qwer';";
            int rows_changed = db_service.runUpdateQuery(query);
            
            assertTrue(rows_changed < 1);

            // String reset_update_data = "UPDATE User SET username = 'qwer', password = 'qwer', first_name = 'qwer', last_name = 'qwer' WHERE username = 'asdf';";
            // db_service.runUpdateQuery(reset_update_data);

        } catch (Exception e) {
            assertTrue(e.getMessage(), true);;
        }
    }

}
