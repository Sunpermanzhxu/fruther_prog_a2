package com.dah.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dah.utility.FileUtillity;

public class DBServices {

    private String db_path;

    private Connection connection;
    
    public DBServices() {
        db_path = FileUtillity.DB_PATH;
        connection = null;

    }

    public void connectToDB() throws Exception {
         
        try {
            // Register the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:" + db_path);

        } catch (ClassNotFoundException e) {
            String err_message = "SQLite JDBC driver not found!!!";
            throw new ClassNotFoundException(err_message);
        } catch (SQLException e) {
            String err_message = "SQLite database connection error!!!";
            throw new SQLException(err_message);
        }

    }

    public boolean checkConnection() {
        boolean sucess = false;
        if (connection != null) {
            sucess = true;
        }
        return sucess;
    }

    public void closeConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            String err_message = "Error closing the database connection!!!";
            throw new SQLException(err_message);
        }
    }

}
