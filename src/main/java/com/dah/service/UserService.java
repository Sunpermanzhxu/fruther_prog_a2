package com.dah.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dah.model.User;
import com.dah.utility.FileUtillity;

public class UserService {

    private DBService db_service;

    private User user;
    private boolean valid_user;

    private String select_usr_pre;
    private String insert_user_pre;

    public UserService(DBService db_service) {
        this.db_service = db_service;

        this.user = new User();
        this.valid_user = false;

        select_usr_pre = FileUtillity.SELECT_USER_PRE;
        insert_user_pre = FileUtillity.INSERT_USER_PRE;
    }
    

    /**
     * generate a query that finds a user by username and password
     * @param username
     * @param password
     * @return {@code return_query} a string of the query
     */
    private String compileSelectQuery(String username, String password) {
        String return_query = select_usr_pre;

        return_query += " ";

        return_query += "WHERE ";

        return_query += "username = ";
        return_query += "'" + username + "'";

        if (password.length() < 1) {
            return_query += "";
        } else {
            return_query += " AND ";
            return_query += "password = ";
            return_query += "'" + password + "'";
        }

        return_query += ";";

        return return_query;
    }


    /**
     * load a User if retrieving is sucessfull
     * must have both username and password to avoide security issue
     * @param req_username      username to search
     * @param req_password      password to search
     * @throws IllegalArgumentException     if username and password is not in db
     * @throws Exception        if there is error in connection 
     */
    public void retriveUserFromDB(String req_username, String req_password) throws IllegalArgumentException, Exception {
        
        valid_user = false;
        
        try {
            Statement statement = db_service.getStatement();

            // get resutls
            String query = compileSelectQuery(req_username, req_password);
            ResultSet resultSet = statement.executeQuery(query);

            // process user_data
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                
                this.user = new User(username, password, first_name, last_name);
            }

            statement.close();

            if (this.user.getUsername().equals("")) {
                String err_message = "Username or password is incorect!!!";
                valid_user = false;
                throw new IllegalArgumentException(err_message);
            } else {
                valid_user = true;
            }

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            String err_message = "Error, no connection to be found!!!";
            throw new Exception(err_message);
        }
        
    }


    public User getUser(){
        return this.user;
    }

    public boolean getValidity() {
        return valid_user;
    }


    // area for registing ----------------------------------------------------

    /**
     * generate a query to add a user
     * @param username
     * @param password
     * @param f_name
     * @param l_name
     * @return {@code return_query} a string of the query
     */
    public String compileInsertQuery(String username, String password, String f_name, String l_name) {
        String return_query = insert_user_pre;

        return_query += "(";

        return_query += "'" + username + "', ";
        return_query += "'" + password + "', ";
        return_query += "'" + f_name + "', ";
        return_query += "'" + l_name + "'";

        return_query += ");";

        return return_query;
    }


    /**
     * the actuall part where stores the data
     * @param username
     * @param password
     * @param f_name
     * @param l_name
     * @throws SQLException
     * @throws IllegalArgumentException
     */
    private void storeAccount(String username, String password, String f_name, String l_name) throws SQLException, IllegalArgumentException {
        try {
            Statement statement = db_service.getStatement();

            String query = compileInsertQuery(insert_user_pre, insert_user_pre, select_usr_pre, insert_user_pre);
            
            // int row_change = statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            String err_message = "Error, no connection to be found!!!";
            throw new SQLException(err_message);
            
        } catch (IllegalArgumentException e) {
            String err_message = "Error, username already exist!!!";
            throw new IllegalArgumentException(err_message);
        }
    }

    public void registUser(String username, String password, String f_name, String l_name) throws IllegalArgumentException, SQLException {

        storeAccount(username, password, f_name, l_name);

    }

    // TODO: register and getInfo


}
