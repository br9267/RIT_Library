package com.example.rit_library;

import java.util.ArrayList;

public class Review {
    public String book_id;
    public String review_grade;
    public String user_feedback;
    public String user_recommendation;
    RIT_LibraryDatabase database;

    public Review() {
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public Review(String book_id) {
        this.book_id = book_id;
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getReview_grade() {
        return review_grade;
    }

    public void setReview_grade(String review_grade) {
        this.review_grade = review_grade;
    }

    public String getUser_feedback() {
        return user_feedback;
    }

    public void setUser_feedback(String user_feedback) {
        this.user_feedback = user_feedback;
    }

    public String getUser_recommendation() {
        return user_recommendation;
    }

    public void setUser_recommendation(String user_recommendation) {
        this.user_recommendation = user_recommendation;
    }

    public Review(String book_id, String review_grade, String user_feedback, String user_recommendation) {
        this.book_id = book_id;
        this.review_grade = review_grade;
        this.user_feedback = user_feedback;
        this.user_recommendation = user_recommendation;
        database = RIT_LibraryDatabase.getInstance();
        database.connect();

    }
    public boolean fetch(){
         String sqlStatement = "SELECT * FROM review WHERE book_id = ?;";
         ArrayList<String> array = new ArrayList<String>();
         array.add(getBook_id());
        ArrayList<ArrayList<String>> list = database.getData(sqlStatement,array,false);
        if(list.get(0).isEmpty()){
            return false;
        }
        else{
            setReview_grade(list.get(0).get(1));
            setUser_feedback(list.get(0).get(2));
            setUser_recommendation(list.get(0).get(3));
            return true;
        }

    }
    public void put(){
        String sqlStatement = "INSERT INTO review (book_id, review_grade,user_feedback,user_recommendation) VALUES (?,?,?,?)";
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getBook_id());
        array.add(this.getReview_grade());
        array.add(this.getUser_feedback());
        array.add(this.getUser_recommendation());
        database.setData(sqlStatement, array);
    }

    public void update(){
        String sqlStatement = "UPDATE review SET review_grade = ?, user_feedback = ?, user_recommendation = ? WHERE book_id = ?";
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getReview_grade());
        array.add(this.getUser_feedback());
        array.add(this.getUser_recommendation());
        array.add(this.getBook_id());
        database.setData(sqlStatement, array);
    }
}
