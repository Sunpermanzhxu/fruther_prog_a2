package com.dah.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dah.utility.FileUtillity;

public class DBService {

    private String db_path;

    private Connection connection;
    
    public DBService() {
        db_path = FileUtillity.DB_PATH;
        connection = null;

    }


    /**
     * load the .db file for both users and posts
     * the .db file is fixed to the {@code DB_PATH} deinfied in FileUtillity.java
     * @throws ClassNotFoundException if a database file is not found
     * @throws SQLException if a database access error occurs
     */
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


    /**
     * check connection of the db file
     * @return {@code true} if the connection is still on;
     *         {@code false} if it is closed or not connected
     */
    public boolean checkConnection() {
        boolean sucess = false;
        try {
            if (connection != null && !connection.isClosed()) {
                sucess = true;
            }
        } catch (SQLException e) {
        }
        return sucess;
    }


    /**
     * close the connection to the db file
     * @throws SQLException if a database access error occurs
     */
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
