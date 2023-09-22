package com.dah.utility;

public class FileUtillity {
    
    // public static final String USER_FILE_PATH = "/resources/users.csv";

    // public static final String POST_FILE_PATH = "/resources/posts.csv";

    // public static final String TEST_USER_FILE_PATH = "src/test/java/com/dah/test_data/users.csv";

    public static final String DB_PATH = "/resources/dah.db";


    // querries

    public static final String SELECT_USER_BY_ID_PRE = "SELECT * FROM User";

    public static final String SELECT_POST_List_PRE = "SELECT po.ID, po.content, po.author, po.likes, po.shares, po.date_time FROM user_post_rela up JOIN User us ON up.username = us.username JOIN Post po ON up.ID = po.ID ";


    // the relation table will auto update username
    public static final String UPDATE_USER_PRE = "UPDATE User SET ";

    public static final String INSERT_USER_PRE = "INSERT INTO User (username, password, first_name, last_name) VALUES ";


    // the relation table will auto delete
    public static final String DELETE_POST_PRE = "DELETE FROM Post ";


    public static final String INSERT_POST_PRE = "INSERT INTO Post (ID, content, author, likes, shares, date_time) VALUES ";
    
    // also need insert on relation table
    public static final String INSERT_RELA_PRE = "INSERT INTO user_post_rela (username, ID) VALUES ";

}
