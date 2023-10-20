package com.dah.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.Test;

/**
 * made becaused of special popertity of post
 */
public class postTest {

    @Test
    public void createPostSucessTest() {
        int id = 0;
        String content = "qwer qwer";
        String author = "asdf";
        int likes = 3;
        int shares = 4;
        String date_time = "12/12/1212 12:12";

        try {
            Post post = new Post(id, content, author, likes, shares, date_time);
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void createPostInvalidIDTest() {
        int id = -3;
        String content = "qwer qwer";
        String author = "asdf";
        int likes = 3;
        int shares = 4;
        String date_time = "12/12/1212 12:12";

        try {
            Post post = new Post(id, content, author, likes, shares, date_time);
            fail("should not continue");
        } catch (IllegalArgumentException e) {
            assertEquals("Error: id can not be negative(< 0)!!!", e.getMessage());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void createPostInvalidCommaTest() {
        int id = 0;
        String content = "qwer, qwer";
        String author = "asdf";
        int likes = 3;
        int shares = 4;
        String date_time = "12/12/1212 12:12";

        try {
            Post post = new Post(id, content, author, likes, shares, date_time);
            fail("should not continue");
        } catch (IllegalArgumentException e) {
            assertEquals("Error: content can not have comma(,)!!!", e.getMessage());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void createPostInvalidLikesTest() {
        int id = 0;
        String content = "qwer qwer";
        String author = "asdf";
        int likes = -3;
        int shares = 4;
        String date_time = "12/12/1212 12:12";

        try {
            Post post = new Post(id, content, author, likes, shares, date_time);
            fail("should not continue");
        } catch (IllegalArgumentException e) {
            assertEquals("Error: likes can not be negative(< 0)!!!", e.getMessage());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void createPostInvalidSharesTest() {
        int id = 0;
        String content = "qwer qwer";
        String author = "asdf";
        int likes = 3;
        int shares = -4;
        String date_time = "12/12/1212 12:12";

        try {
            Post post = new Post(id, content, author, likes, shares, date_time);
            fail("should not continue");
        } catch (IllegalArgumentException e) {
            assertEquals("Error: shares can not be negative(< 0)!!!", e.getMessage());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void createPostInvalidDateTimeTest() {
        int id = 0;
        String content = "qwer qwer";
        String author = "asdf";
        int likes = 3;
        int shares = 4;
        String date_time = "12/12/121212:12";

        try {
            Post post = new Post(id, content, author, likes, shares, date_time);
            fail("should not continue");
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        } catch (ParseException e) {
            assertEquals("Error: date format not correct!!!", e.getMessage());
        }
    }
    
}
