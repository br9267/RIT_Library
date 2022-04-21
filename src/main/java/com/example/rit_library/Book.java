package com.example.rit_library;

import java.util.ArrayList;

public class Book {
    private int book_id = 0;
    private String publisher;
    private String course = "";
    private String description;
    private int isbn;
    private String title;
    private String genre;
    private String format;
    private String state;
    private String year;
    RIT_LibraryDatabase database;

    public Book(int book_id, String publisher, String course, String description, int isbn,String year, String title, String genre, String format, String state, RIT_LibraryDatabase database) {
        this.book_id = book_id;
        this.publisher = publisher;
        this.course = course;
        this.description = description;
        this.isbn = isbn;
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.format = format;
        this.state = state;
        this.database = database;
    }

    public void postP() {
        database.connect();
        String sqlStatement = "INSERT INTO book(book_id,book_publisher_id,book_course_id,book_description,book_isbn,book_published_year,book_title,book_genre,format_id,book_state) VALUES (?,?,?,?,?,?,?,?,?,?)";
        ArrayList<String> list = new ArrayList<>();
        list.add(String.valueOf(this.book_id));
        list.add(this.publisher);
        list.add(this.course);
        list.add(this.description);
        list.add(String.valueOf(this.isbn));
        list.add(this.year);
        list.add(this.title);
        list.add(this.genre);
        list.add(this.format);
        list.add(this.state);
        System.out.println(database.setData(sqlStatement,list));
        database.close();
    }


}
