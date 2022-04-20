package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField fieldID;
    public TextField fieldTitle;
    public TextField fieldISBN;
    public TextField fieldYear;
    public TextField fieldDescription;
    public Button CancelButton;
    public Button saveButton;
    public TextField fieldPublisher;
    public TextField fieldGenre;
    public TextField fieldFormat;
    public TextField fieldState;
    public TextField fieldCourse;

    RIT_LibraryDatabase database;


    public void cancelAddBook(ActionEvent actionEvent) {
        Stage stage = (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addBook(ActionEvent actionEvent) {
        String id = fieldID.getText();
        String publisher = fieldPublisher.getText();
        String title = fieldTitle.getText();
        String course = fieldCourse.getText();
        String description = fieldDescription.getText();
        String isbn = fieldISBN.getText();
        String year = fieldYear.getText();
        String genre = fieldGenre.getText();
        String format = fieldFormat.getText();
        String state = fieldState.getText();
        if (id.isEmpty() || publisher.isEmpty() || title.isEmpty() || course.isEmpty() || description.isEmpty()|| isbn.isEmpty()|| year.isEmpty()|| genre.isEmpty()|| format.isEmpty()|| state.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all of the fields");
            alert.showAndWait();
        }
        else{
            Book book = new Book(Integer.parseInt(id),publisher,course,description,Integer.parseInt(isbn),year,title,genre,format,state,database);
            book.postP();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = new RIT_LibraryDatabase("jdbc:" + "mysql", "localhost", "3306", "RIT_Library", "root","Rktrnje2000");
        database.connect();
    }
}