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

    public Book(String id) {
        this.book_id = Integer.parseInt(id);
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public Book() {
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
    public void updateWhenBorrow(){
       String sqlStatement = "UPDATE book SET book_available = ? WHERE book_id = ?";
       ArrayList<String> array = new ArrayList<String>();
       array.add(String.valueOf(0));
       array.add(String.valueOf(getBook_id()));
       database.setData(sqlStatement,array);
    }
    public void fetch(){
        String sqlStatement = "SELECT * FROM book WHERE book_id = ?";
        ArrayList<String> array = new ArrayList<String>();
        array.add(String.valueOf(this.getBook_id()));
        ArrayList<ArrayList<String>> list= database.getData(sqlStatement, array,false);
        setPublisher(list.get(0).get(1));
        setCourse(list.get(0).get(2));
        setDescription(list.get(0).get(3));
        setIsbn(Integer.parseInt(list.get(0).get(4)));
        setYear(list.get(0).get(5));
        setTitle(list.get(0).get(6));
        setGenre(list.get(0).get(7));
        setFormat(list.get(0).get(8));
        setState(list.get(0).get(9));
    }
}
