package com.dah.service;

import java.util.ArrayList;

import com.dah.model.Post;
import com.dah.utility.FileUtillity;

public class PostService {

    private DBService db_service;

    private ArrayList<Post> posts;

    private String select_post_list_pre;

    public PostService(DBService db_service) {
        this.db_service = db_service;

        this.posts = new ArrayList<Post>();

        select_post_list_pre = FileUtillity.SELECT_POST_List_PRE;
    }
    
    
}
