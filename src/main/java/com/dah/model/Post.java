package com.dah.model;

import java.text.ParseException;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class Post {
    private int id;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private String date_time;
    private Date date_date_time;

    
    private SimpleDateFormat date_format;

    public Post(int id, String content, String author, int likes, int shares, String date_time) throws ParseException, IllegalArgumentException {
        this.date_format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        this.id = id;
        if (this.id < 0) {
            // duplicated id is checked elsewhere
            String err_message = "Error: id can not be negative(< 0)!!!";
            throw new IllegalArgumentException(err_message);
        }

        this.content = content;
        if (this.content.contains(",")) {
            String err_message = "Error: content can not have comma(,)!!!";
            throw new IllegalArgumentException(err_message);
        }

        this.author = author;

        this.likes = likes;
        if (this.likes < 0) {
            String err_message = "Error: likes can not be negative(< 0)!!!";
            throw new IllegalArgumentException(err_message);
        }

        this.shares = shares;
        if (this.shares < 0) {
            String err_message = "Error: shares can not be negative(< 0)!!!";
            throw new IllegalArgumentException(err_message);
        }

        this.date_time = date_time;
        this.date_date_time = parseDate(date_time);

    }

    /*
     * @brief remove the anoying convertion in main
     */
    private Date parseDate(String str_date) throws ParseException {

        Date temp_date = new Date();
        try {
            if (str_date.length() < 15) {
                String err_message = "Error: date format not correct!!!";
                int err_position = 0;
                throw new ParseException(err_message, err_position);
            }
            temp_date = date_format.parse(str_date);
        } catch (ParseException e) {
            String err_message = "Error: date format not correct!!!";
            int err_position = e.getErrorOffset();
            throw new ParseException(err_message, err_position);
        }
        return temp_date;
    }
    
    // get methods -----------------------------------
    public int getID() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

    public int getShares() {
        return shares;
    }

    public String strDateTime() {
        String formatted_date = date_format.format(this.date_time);
        return formatted_date;
    }

    public String getDateTime() {
        return date_time;
    }

    public SimpleIntegerProperty idProperty() {
        SimpleIntegerProperty  SIP_id = new SimpleIntegerProperty();
        SIP_id.set(id);
        return SIP_id;
    }

    public SimpleStringProperty contentProperty() {
        SimpleStringProperty SSP_content = new SimpleStringProperty();
        SSP_content.set(content);
        return SSP_content;
    }

    public SimpleStringProperty authorProperty() {
        SimpleStringProperty SSP_author = new SimpleStringProperty();
        SSP_author.set(author);
        return SSP_author;
    }

    public SimpleIntegerProperty likesProperty() {
        SimpleIntegerProperty  SIP_likes = new SimpleIntegerProperty();
        SIP_likes.set(likes);
        return SIP_likes;
    }

    public SimpleIntegerProperty sharesProperty() {
        SimpleIntegerProperty  SIP_shares = new SimpleIntegerProperty();
        SIP_shares.set(shares);
        return SIP_shares;
    }

    public SimpleStringProperty dateTimeProperty() {
        SimpleStringProperty SSP_date_time = new SimpleStringProperty();
        SSP_date_time.set(strDateTime());
        return SSP_date_time;
    }

    // -------------------------------------------------

    
    /*
     * Override the toString() method
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
