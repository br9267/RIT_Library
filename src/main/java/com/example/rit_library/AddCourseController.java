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

public class AddCourseController implements Initializable {
    RIT_LibraryDatabase database;
    @FXML
    private TextField courseName;

    @FXML
    private TextField courseType;

    @FXML
    void AddCourse(ActionEvent event) {
        String name = courseName.getText();
        String type = courseType.getText();
        if (name.isBlank() || type.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all of the required fields");
            alert.showAndWait();
        } else {
            String insertCourse = "INSERT INTO course VALUES (?,?)";
            ArrayList<String> list = new ArrayList<>();
            list.add(name);
            list.add(type);
            database.setData(insertCourse, list);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
