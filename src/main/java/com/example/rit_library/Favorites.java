package com.example.rit_library;

import java.util.ArrayList;

public class Favorites {
    private int favorites_id = 0;
    private String book_id;
    private String user_id;

    public int getFavorites_id() {
        return favorites_id;
    }

    public void setFavorites_id(int favorites_id) {
        this.favorites_id = favorites_id;
    }

    private RIT_LibraryDatabase database;
    public Favorites(){
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public Favorites(String book_id, String user_id) {
        this.book_id = book_id;
        this.user_id = user_id;
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean setFavorite() {
        String sqlStatement = "SELECT * FROM favorites WHERE book_id = ? AND user_id = ?;";
        ArrayList<String> list = new ArrayList<String>();
        list.add(getBook_id());
        list.add(getUser_id());
        ArrayList<ArrayList<String>> data =    database.getData(sqlStatement, list, false);
        if(data.get(0).isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void saveFavorite() {
        String sqlStatement = "INSERT INTO favorites (favorites_id, book_id, user_id) VALUES (?, ? , ?);";
        ArrayList<String> list = new ArrayList<String>();
        list.add(String.valueOf(getFavorites_id()));
        list.add(getBook_id());
        list.add(getUser_id());
        database.setData(sqlStatement, list);
    }
}
