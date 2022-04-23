package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectedBook implements Initializable {
    public Button favoriteButton;
    bookDatabase book;

    public void setBook(bookDatabase book) {
        this.book = book;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addToFavorites(ActionEvent actionEvent) {
        Book searchBook = new Book(book.getId());
        
    }
}
