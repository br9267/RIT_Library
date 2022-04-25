package com.example.rit_library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class signUpController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> comboRole;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userID;

    @FXML
    private PasswordField userPassword;


    public TextField userType;

    public TextField userName;
    public Button createUserButton;


    RIT_LibraryDatabase database;
    ObservableList<String> comboFill = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = RIT_LibraryDatabase.getInstance();
        database.connect();
        fillComboBox();

    }

    @FXML
    void signUpUser(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        String id = userID.getText();
        String pass = userPassword.getText();
        String email = userEmail.getText();
        String comboChoice = (String) comboRole.getValue();

        if (id.isEmpty() || email.isEmpty() || comboRole.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all of the required fields");
            alert.showAndWait();
        } else {
            String getAllUsers = "SELECT * FROM user;";
            ArrayList<ArrayList<String>> list = database.getAllData(getAllUsers);
            boolean alreadyRegistered = false;
            for (ArrayList<String> l : list) {
                if (l.get(0).equals(id))
                {
                    alreadyRegistered = true;
                    break;
                }
            }
            if(alreadyRegistered)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "A user with that userID already exists");
                alert.showAndWait();
            }
            else
            {

                User user = new User(id, comboChoice, email);
                user.createUser();
                UserLogin userLogin = new UserLogin(id,pass);
                userLogin.createLoginInfo();
                CurrentUser userCurrent = CurrentUser.getInstance();
                userCurrent.setUser(new User(id));
                Utils.changeScene("book_table.fxml");
            }
        }
    }

    public void fillComboBox() {
        String sqlStatement = "SELECT * FROM Role;";
        ArrayList<ArrayList<String>> list = database.getAllData(sqlStatement);
        for (ArrayList<String> object : list) {
            comboFill.add(object.get(0));
        }
        comboRole.setItems(comboFill);
    }

    public void cancelAction(ActionEvent actionEvent) throws IOException {
       Utils.changeScene("loginWindow.fxml");
    }

}
