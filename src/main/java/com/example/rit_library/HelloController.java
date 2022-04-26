package com.example.rit_library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField fieldID;
    public TextField fieldTitle;
    public TextField fieldISBN;
    public TextField fieldYear;
    public TextField fieldDescription;
    public Button CancelButton;
    public Button saveButton;


    public TextField fieldFormat;
    public TextField fieldState;


    RIT_LibraryDatabase database;
    @FXML
    private ChoiceBox<String> fieldCourse;
    @FXML
    private ChoiceBox<String> fieldGenre;
    @FXML
    private ChoiceBox<String> fieldPublisher;

    public void cancelAddBook(ActionEvent actionEvent) {
        Stage stage = (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addBook(ActionEvent actionEvent) {
        String id = fieldID.getText();
        String publisher = fieldPublisher.getValue();
        String title = fieldTitle.getText();
        String course = fieldCourse.getValue();
        String description = fieldDescription.getText();
        String isbn = fieldISBN.getText();
        String year = fieldYear.getText();
        String genre = fieldGenre.getValue();
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
            database = RIT_LibraryDatabase.getInstance();
            database.connect();
            String sqlStatement = "SELECT * FROM course";
            ArrayList<ArrayList<String>> courses = database.getAllData(sqlStatement);
            ArrayList<String> allCourseStrings = new ArrayList<>();
            for (ArrayList<String> object : courses) {
                allCourseStrings.add(object.get(0));
            }
            ObservableList<String> observableCourses = FXCollections.observableList(allCourseStrings);
            fieldCourse.setItems(observableCourses);
            if (observableCourses.size() > 0)
                fieldCourse.setValue(observableCourses.get(0));

            String genreStatement = "SELECT * FROM genre";
            ArrayList<ArrayList<String>> genres = database.getAllData(genreStatement);
            ArrayList<String> allGenreStrings = new ArrayList<>();
            for (ArrayList<String> object : genres) {
                allGenreStrings.add(object.get(0));
            }
            ObservableList<String> observableGenres = FXCollections.observableList(allGenreStrings);
            fieldGenre.setItems(observableGenres);
            if (observableGenres.size() > 0)
                fieldGenre.setValue(observableGenres.get(0));


            String publisherStatement = "SELECT * FROM publisher";
            ArrayList<ArrayList<String>> publishers = database.getAllData(publisherStatement);
            ArrayList<String> allPublishersStrings = new ArrayList<>();
            for (ArrayList<String> object : publishers) {
                allPublishersStrings.add(object.get(0));
            }
            ObservableList<String> observablePublishers = FXCollections.observableList(allPublishersStrings);
            fieldPublisher.setItems(observablePublishers);
            if (observablePublishers.size() > 0)
                fieldPublisher.setValue(observablePublishers.get(0));


            database.close();


    }
}