package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    public Button searchButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void loadWindow(String loc, String title){
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource(loc));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
    }

    public void loadSearch(ActionEvent actionEvent) {
        loadWindow("book_table.fxml","Search for Books");
    }

    public void loadAdd(ActionEvent actionEvent) {
        loadWindow("hello-view.fxml","Add Books");
    }
}
