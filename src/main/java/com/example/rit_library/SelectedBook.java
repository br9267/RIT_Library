package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectedBook implements Initializable {
    public Button favoriteButton;
    public Button reviewButton;
    public Button borrowButton;
    bookDatabase book;
    User user;

    public void setBook(bookDatabase book) {
        this.book = book;

    }
    public void setUser(User user){
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addToFavorites(ActionEvent actionEvent) {
        Book searchBook = new Book(book.getId());

        
    }

    public void borrowBook(ActionEvent actionEvent) {
    }

    public void reviewBook(ActionEvent actionEvent) {
    }
}
