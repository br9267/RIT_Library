package com.example.rit_library;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

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

    public boolean authenticate(String id, String password) throws NoSuchAlgorithmException, IOException {
        database.connect();
        ArrayList<String> login = new ArrayList<String>();
        String sqlStatement = "SELECT * FROM Login_credentials WHERE user_id = ?;";
        login.add(id);
        ArrayList<ArrayList<String>> login_credentials= database.getData(sqlStatement, login, true);


        if(login_credentials.get(1).isEmpty())
            return false;
        String realPassword = login_credentials.get(1).get(1);
        String comparisonPassword = Utils.generateHash(password);

        if(realPassword.equals(comparisonPassword)){
            this.setUsername(id);
            this.setPassword(password);
            CurrentUser user = CurrentUser.getInstance();
            user.setUser(new User(getUsername()));
            Utils.changeScene("book_table.fxml");
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"Your username/password is wrong");
            alert.showAndWait();
            return false;
        }
    }



    public void createLoginInfo() throws NoSuchAlgorithmException {
        database.connect();
        String sqlStatement = "INSERT INTO login_credentials (user_id, password) VALUES (?,?)";
        ArrayList<String> list = new ArrayList<>();
        list.add(getUsername());
        list.add(Utils.generateHash(getPassword()));
        database.setData(sqlStatement, list);
    }

    public boolean login(String id, String password) throws IOException, NoSuchAlgorithmException {
       // HelloApplication.changeScene("book_table.fxml");

        if(authenticate(id, password)){
            return true;
        }
        else{


            return false;
        }
    }

}
