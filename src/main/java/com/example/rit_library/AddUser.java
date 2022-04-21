package com.example.rit_library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddUser implements Initializable {
    public TextField userID;
    public TextField userType;
    public TextField userEmail;
    public TextField userName;
    public Button createUserButton;
    public ComboBox comboRole;
    public Button cancelButton;
    RIT_LibraryDatabase database;
    ObservableList<String> comboFill = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       database = RIT_LibraryDatabase.getInstance();
        database.connect();
        fillComboBox();

    }
    public void fillComboBox(){
        String sqlStatement = "SELECT * FROM Role;";
        ArrayList<ArrayList<String>> list = database.getAllData(sqlStatement);
        for(ArrayList<String> object : list){
            comboFill.add(object.get(0));
        }
        comboRole.setItems(comboFill);
    }
    public void addUser(ActionEvent actionEvent) {
        String id = userID.getText();
        String email = userEmail.getText();
        String comboChoice = (String) comboRole.getValue();
        if(id.isEmpty() || email.isEmpty() || comboRole.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all of the required fields");
            alert.showAndWait();
        }else{
            User user = new User(id, comboChoice, email);
            user.createUser();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
