package com.dah.service;

import java.sql.ResultSet;
import java.sql.Statement;

import com.dah.model.User;
import com.dah.utility.FileUtillity;

public class UserService {

    private DBService db_service;

    private User user;
    private boolean valid_user;

    private String select_usr_pre;

    public UserService(DBService db_service) {
        this.db_service = db_service;

        this.user = new User();
        this.valid_user = false;

        select_usr_pre = FileUtillity.SELECT_USER_PRE;
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

        return_query += " AND ";

        return_query += "password = ";
        return_query += "'" + password + "'";

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
        try {
            Statement statement = db_service.getStatement();

            // get resutls
            String query = this.compileSelectQuery(req_username, req_password);
            ResultSet resultSet = statement.executeQuery(query);


            // process user_data
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                
                this.user = new User(username, password, first_name, last_name);
            }

            if (this.user.getUsername().equals("")) {
                String err_message = "Error, no User with username: " + req_username + " !!!";
                throw new IllegalArgumentException(err_message);
            }

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            String err_message = "Error, no connection to be found!!!";
            throw new Exception(err_message);
        }
        
    }


    /**
     * check login status
     * @return {@code valid_user} the bullena value
     */
    public boolean checkLogin() {
        return valid_user;
    }


    public User getUser(){
        return this.user;
    }

    // TODO: register and getInfo


}
