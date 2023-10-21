package com.dah.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import com.dah.model.Post;
import com.dah.model.User;
import com.dah.utility.FileUtillity;

public class PostService {

    private DBService dbService;
    // private UserService userService;

    private ArrayList<Post> posts;

    private String add_post_pre;
    private String select_post_list_pre;

    private String insert_rela_pre;

    public PostService(DBService dbService) {
        this.dbService = dbService;
        // this.userService = userService;

        this.posts = new ArrayList<Post>();

        add_post_pre = FileUtillity.INSERT_POST_PRE;
        select_post_list_pre = FileUtillity.SELECT_POST_List_PRE;

        insert_rela_pre = FileUtillity.INSERT_RELA_PRE;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
    
    private String compileInsertQuery(String ID, String content, String author, String likes, String shares, String date_time) {
        String return_query = add_post_pre;

        return_query += "(";

        return_query += "" + ID + ", ";
        return_query += "'" + content + "', ";
        return_query += "'" + author + "', ";
        return_query += "" + likes + ", ";
        return_query += "" + shares + ", ";
        return_query += "'" + date_time + "'";

        return_query += ");";

        return return_query;
    }

    private String compileInsertRelaQuery(String username, String ID) {
        String return_query = insert_rela_pre;

        return_query += "(";

        return_query += "'" + username + "', ";
        return_query += "" + ID + "";

        return_query += ");";

        return return_query;
    }

    public void add_a_post(String ID, String content, String author, String likes, String shares, String date_time, User user) throws SQLException, IllegalArgumentException, AssertionError {
        try {
            String query = compileInsertQuery(ID, content, author, likes, shares, date_time);
            int rows_changed = dbService.runUpdateQuery(query);

            String username = user.getUsername();

            String rela_query = compileInsertRelaQuery(username, ID);
            int rela_rows_changed = dbService.runUpdateQuery(rela_query);

            if (rows_changed * rela_rows_changed < 1) {
                // no data stored
                String err_message = "Invalid ID or username";
                throw new IllegalArgumentException(err_message);
            }
        } catch (SQLException e) {
            String err_message = "Error: Database connection error!!!";
            throw new SQLException(err_message);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (AssertionError e) {
            String err_message = "ID unaviliable!";
            throw new SQLException(err_message);
        }
    }


    // get post --------------------------------------

    private String compileRetriveQuery(String id, User user) {
        String return_query = select_post_list_pre;

        return_query += "WHERE ";

        return_query += "ID = ";
        return_query += "" + id + " ";
        
        return_query += "AND ";

        return_query += "username = ";
        return_query += "'" + user.getUsername() + "'";

        return_query += ";";

        return return_query;
    }

    public void retrivePost(String str_id, User user) throws IllegalArgumentException, ParseException, SQLException {
        try {
            String query = compileRetriveQuery(str_id, user);

            Statement statement = dbService.getStatement();

            ResultSet resultSet = statement.executeQuery(query);

            int result_count = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String content = resultSet.getString("content");
                String author = resultSet.getString("author");
                int likes = resultSet.getInt("likes");
                int shares = resultSet.getInt("shares");
                String date_time = resultSet.getString("date_time");

                Post recived_post = new Post(id, content, author, likes, shares, date_time);
                posts.add(recived_post);
                result_count += 1;
            }

            if (result_count < 1) {
                // no data got
                String err_message = "Invalid id";
                throw new IllegalArgumentException(err_message);
            }

            statement.close();

        } catch (SQLException e) {
            String err_message = "Error: Database connection error!!!";
            throw new SQLException(err_message);
        }
        
    }
    //TODO: the post actions.
    
}
