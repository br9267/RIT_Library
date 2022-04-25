package com.example.rit_library;

public class CurrentUser {
    private User user;
    private final static CurrentUser INSTANCE = new CurrentUser();

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        return INSTANCE;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return this.user;
    }
}
