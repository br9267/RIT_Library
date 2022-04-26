package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPublisherController implements Initializable {
    RIT_LibraryDatabase database;
    @FXML
    private TextField publisherCity;

    @FXML
    private TextField publisherName;

    @FXML
    void addPublisher(ActionEvent event) {
        database.connect();
        String city = publisherCity.getText();
        String name = publisherName.getText();
        if (name.isBlank() || city.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all of the required fields");
            alert.showAndWait();
        } else {
            String insertCourse = "INSERT INTO publisher VALUES (?,?)";
            ArrayList<String> list = new ArrayList<>();
            list.add(name);
            list.add(city);
            database.setData(insertCourse, list);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        database.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = RIT_LibraryDatabase.getInstance();

    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
