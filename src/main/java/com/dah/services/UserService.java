package com.dah.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.dah.model.User;

public class UserService {

    private ArrayList<User> users;

    public UserService() {
        users = new ArrayList<User>();
    }

    public void loadUsers(String user_file_path) throws Exception {
        String line;
        
        try {
            File myObj = new File(user_file_path);
            Scanner myReader = new Scanner(myObj);

            line = myReader.nextLine();
            // Read and process data lines
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                String[] data = line.split(",");

                String username = data[0];
                String password = data[1];
                String first_name = data[2];
                String last_name = data[3];
                
               
                User read_user = new User(username, password, first_name, last_name);
                users.add(read_user);

            }

            myReader.close();

        } catch (Exception e) {
            throw e;
        }
    }
    

    // find index of user by username
    // return -1 if not found
    public int findUserIndex(String username) {

        boolean found = false;

        int pointer = 0;

        while (!found && pointer < users.size()) {
            User user_i = users.get(pointer);
            if (username.equals(user_i.getUsername()) ) {
                found = true;
            } else {
                pointer += 1;
            }
        }

        if (!found) {
            pointer = -1;
        }

        return pointer;

    }


    public int getUserNum() {
        return users.size();
    }

}
