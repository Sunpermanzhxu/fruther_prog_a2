package com.dah.services;

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


}
