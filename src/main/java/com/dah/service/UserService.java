package com.dah.service;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dah.model.User;
import com.dah.utility.FileUtillity;

public class UserService {

    private DBService db_service;

    private User user;
    private boolean valid_user;

    private String select_user_pre;
    private String insert_user_pre;
    private String update_user_pre;

    public UserService(DBService db_service) {
        this.db_service = db_service;

        this.user = new User();
        this.valid_user = false;

        select_user_pre = FileUtillity.SELECT_USER_PRE;
        insert_user_pre = FileUtillity.INSERT_USER_PRE;
        update_user_pre = FileUtillity.UPDATE_USER_PRE;
    }
    

    /**
     * generate a query that finds a user by username and password
     * @param username
     * @param password
     * @return {@code return_query} a string of the query
     */
    private String compileSelectQuery(String username, String password) {
        String return_query = select_user_pre;

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
     * must only have user result form a query
     * @param req_username      username to search
     * @param req_password      password to search
     * @throws SQLException
     * @throws IllegalArgumentException     if username and password is not in db
     * @throws Exception        if there is error in connection 
     */
    public void retriveUserFromDB(String req_username, String req_password) throws SQLException {
        try {
            String query = compileSelectQuery(req_username, req_password);

            Statement statement = db_service.getStatement();

            ResultSet resultSet = statement.executeQuery(query);

            // data hold
            String username = "";
            String password = "";
            String first_name = "";
            String last_name = "";

            while (resultSet.next()) {
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                first_name = resultSet.getString("first_name");
                last_name = resultSet.getString("last_name");
            }

            if (username.length() < 1) {
                // no data got
                String err_message = "Invalid username or password";
                throw new IllegalArgumentException(err_message);
            } else {
                user = new User(username, password, first_name, last_name);
                valid_user = true;
            }

            statement.close();

        } catch (SQLException e) {
            String err_message = "Error: Database connection error!!!";
            throw new SQLException(err_message);
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
    private String compileInsertQuery(String username, String password, String f_name, String l_name) {
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
    private void storeAccount(String username, String password, String f_name, String l_name) throws IllegalArgumentException, SQLException {
        
        try {
            String query = compileInsertQuery(username, password, f_name, l_name);

            int rows_changed = db_service.runUpdateQuery(query);

            if (rows_changed < 1) {
                // no data stored
                String err_message = "Invalid username or password";
                throw new IllegalArgumentException(err_message);
            }
        } catch (SQLException e) {
            String err_message = "";
            throw new SQLException(err_message);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void registUser(String username, String password, String f_name, String l_name) throws IllegalArgumentException, SQLException {

        storeAccount(username, password, f_name, l_name);

    }

    // update part ---------------------------------------------------------

    private String compileUpdateQuery(String new_username, String new_password, String new_f_name, String new_l_rname, User target_user) {
        String return_string = update_user_pre;

        return_string += "username = '" + new_username + "', ";
        return_string += "password = '" + new_username + "', ";
        return_string += "first_name = '" + new_username + "', ";
        return_string += "last_name = '" + new_username + "' ";

        return_string += "WHERE username = '" + target_user.getUsername() + "';";

        return return_string;
    }

    private void updateuUser(String new_username, String new_password, String new_f_name, String new_l_rname, User target_user) throws SQLException, IllegalArgumentException {
        
        try {
            String query = compileUpdateQuery(new_username, new_password, new_f_name, new_l_rname, target_user);

            int rows_changed = db_service.runUpdateQuery(query);

            if (rows_changed < 1) {
                // no data stored
                String err_message = "Invalid username or password";
                throw new IllegalArgumentException(err_message);
            }
        } catch (SQLException e) {
            String err_message = "";
            throw new SQLException(err_message);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void editProfile(String new_username, String new_password, String new_f_name, String new_l_rname, User target_user) throws IllegalArgumentException, SQLException {
        // decide which to update
        // default the new one

        if (new_username.length() == 0) {
            new_username = target_user.getUsername();
        }
        if (new_password.length() == 0) {
            new_password = target_user.getPassword();
        }
        if (new_f_name.length() == 0) {
            new_f_name = target_user.getFirstName();
        }
        if (new_l_rname.length() == 0) {
            new_l_rname = target_user.getLastName();
        }

        updateuUser(new_username, new_password, new_f_name, new_l_rname, target_user);

    }


}
