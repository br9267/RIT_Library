package com.example.rit_library;

import java.util.ArrayList;

public class Role {
    private String user_role;
    private int user_permission;
    RIT_LibraryDatabase database;
    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public int getUser_permission() {
        return user_permission;
    }

    public void setUser_permission(int user_permission) {
        this.user_permission = user_permission;
    }

    public Role(String user_role, int user_permission) {
        this.user_role = user_role;
        this.user_permission = user_permission;
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    public Role() {
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

}
