package com.example.rit_library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
        Scene scene = new Scene(fxmlLoader, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}