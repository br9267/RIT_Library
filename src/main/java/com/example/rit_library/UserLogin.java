package com.example.rit_library;

import java.util.ArrayList;

public class UserLogin {
    public String username;
    public String password;
    public RIT_LibraryDatabase database;
    public UserLogin() {
        database = RIT_LibraryDatabase.getInstance();
    }
    public UserLogin(String username, String password){
        this.username = username;
        this.password = password;
        database = RIT_LibraryDatabase.getInstance();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String id, String password){
        database.connect();
        ArrayList<String> login = new ArrayList<String>();
        String sqlStatement = "SELECT * FROM Login_credentials WHERE user_id = ?;";
        login.add(id);
        ArrayList<ArrayList<String>> login_credentials= database.getData(sqlStatement, login);
        if(login_credentials.get(1).get(0).equals(id) && login_credentials.get(1).get(1).equals(password)){
            System.out.println("here12121");
            this.setUsername(id);
            this.setPassword(password);
            database.close();
            return true;
        }
        else{
            database.close();
            System.out.println("here1]2");
            return false;
        }
    }
    public boolean login(String id, String password){
        database.connect();
        if(authenticate(id, password)){
            System.out.println("here11111111");
            database.close();
            return true;
        }
        else{
            database.close();
            System.out.println("4242423423");
            return false;
        }
    }
}
