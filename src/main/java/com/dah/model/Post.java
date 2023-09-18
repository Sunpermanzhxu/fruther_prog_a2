package com.dah.model;

import java.text.ParseException;
import java.util.Date;

import java.text.SimpleDateFormat;

public class Post {
    private int id;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private Date date_time;

    
    private SimpleDateFormat date_format;

    public Post(int id, String content, String author, int likes, int shares, String date_time) {
        this.date_format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        this.id = id;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        String str_date = date_time;
        this.date_time = parseDate(str_date);

    }

    /*
     * @brief remove the anoying convertion in main
     */
    private Date parseDate(String str_date) {

        Date temp_date = new Date();
        try {
            temp_date = date_format.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp_date;
    }
    
    // get methods -----------------------------------
    public int getID() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public int getLike() {
        return likes;
    }

    public int getShare() {
        return shares;
    }

    public String strDateTime(){
        String formatted_date = date_format.format(this.date_time);
        return formatted_date;
    }
    // -------------------------------------------------

    
    /*
     * @brief Override the toString() method
     */
    @Override
    public String toString() {
        String returnString = "";
        returnString += Integer.toString(id);
        returnString += " | ";
        returnString += content;
        returnString += " | ";
        return returnString;
    }

}
