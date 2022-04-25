package com.example.rit_library;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Book_on_Loan {
    private String user_id;
    private String book_id;

    private Date due_date;
    private RIT_LibraryDatabase database;

    public Book_on_Loan() {
        this.database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Book_on_Loan(String user_id, String book_id, Date due_date) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.due_date = due_date;
        this.database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }
    public void setBookOnLoan(){
        String sqlStatement = "INSERT INTO book_on_loan (user_id, book_id, due_date) VALUES (?,?,?)";
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getUser_id());
        array.add(String.valueOf(this.getBook_id()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(this.getDue_date());
        array.add(date);
        database.setData(sqlStatement,array);
    }
}
