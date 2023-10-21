package com.dah.service;

import java.sql.SQLException;
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

    //TODO: the post actions.
    
}
