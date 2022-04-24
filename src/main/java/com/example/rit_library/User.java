package com.example.rit_library;

import java.util.ArrayList;

public class User {
    public String id;
    public String role;
    public String email;

    public RIT_LibraryDatabase database;
    public User() {
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public User(String id) {
        this.id = id;
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String id, String role, String email) {
        this.id = id;
        this.role = role;
        this.email = email;
        database = RIT_LibraryDatabase.getInstance();
    }

    public void createUser(){
        database.connect();
        String sqlStatement = "INSERT INTO User (user_id, user_role, e_mail) VALUES (?,?,?)";
        ArrayList<String> list = new ArrayList<String>();
        list.add(getId());
        list.add(getRole());
        list.add(getEmail());
        database.setData(sqlStatement, list);

    }

}
