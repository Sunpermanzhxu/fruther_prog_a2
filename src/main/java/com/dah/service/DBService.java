package com.dah.service;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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


    // /**
    //  * make select query run in this file for better management
    //  * @param query the SELECT query to be executed
    //  * @return {@code resultSet} to be processed later in different services
    //  * @throws SQLException if there is a db connection error
    //  */
    // public ResultSet runSelectQuery(String query) throws SQLException {
    //     Statement statement = connection.createStatement();
    //     System.out.println("in dbservices");
    //     System.out.println(query);
    //     ResultSet resultSet = statement.executeQuery(query);


    //     while (resultSet.next()) {
    //         String username = resultSet.getString("username");
    //         System.out.println("db running");
    //         System.out.println(username);
    //     }

    //     // statement.close();

    //     return resultSet;
    // }


    /**
     * make insert, update and delet query run in this file for better management
     * @param query the query to be executed
     * @return {@code row_change} as number of affected rows
     * @throws SQLException if there is a db connection error
     */
    public int runUpdateQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();

        int row_change = statement.executeUpdate(query);

        statement.close();

        return row_change;
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


    // // no longer needed
    /**
     * provide statement for the other sevices that retrive data from database
     * @return {@code statement}
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException {
        Statement statement = connection.createStatement();

        return statement;
    }

}
