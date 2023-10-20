package com.dah.service;

import java.util.ArrayList;

import com.dah.model.Post;
import com.dah.utility.FileUtillity;

public class PostService {

    private DBService dbService;
    // private UserService userService;

    private ArrayList<Post> posts;

    private String select_post_list_pre;

    public PostService(DBService dbService) {
        this.dbService = dbService;
        // this.userService = userService;

        this.posts = new ArrayList<Post>();

        select_post_list_pre = FileUtillity.SELECT_POST_List_PRE;
    }
    
    //TODO: the post actions.
    
    
}
