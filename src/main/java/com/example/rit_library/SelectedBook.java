package com.example.rit_library;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectedBook implements Initializable {
    bookDatabase book;

    public void setBook(bookDatabase book) {
        this.book = book;
        System.out.println(book.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
