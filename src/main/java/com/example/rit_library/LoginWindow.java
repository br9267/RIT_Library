package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindow implements Initializable {
    public TextField usernameField;
    public TextField passwordField;
    public Button signupButton;
    public Button loginButton;
    public int checker = 0;
    public void loginAuthenticate(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(username.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please enter your credentials");
            alert.showAndWait();
        }
        else{
            UserLogin user = new UserLogin();
            System.out.println(user.login(username, password));


        }
    }

    public void signUp(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
