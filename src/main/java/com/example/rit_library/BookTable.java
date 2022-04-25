package com.example.rit_library;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BookTable implements Initializable {

    public TextField searchBooks;
    public TableColumn<bookDatabase, String> titleColumn;
    public TableColumn<bookDatabase, String> authorColumn;
    public TableColumn <bookDatabase, Integer> ISBNColumn;
    public TableColumn<bookDatabase, String> genreColumn;
    public TableColumn<bookDatabase, String> formatColumn;
    public TableColumn<bookDatabase, Integer> availableColumn;
    public TableColumn<bookDatabase, String> idColumn;
    public TableView<bookDatabase> table;
    public Button addButton;
    public Button addUserButton;
    private RIT_LibraryDatabase database;
    private User user;
    public ObservableList<bookDatabase> tableList = FXCollections.observableArrayList();
    public void showBooks(ActionEvent actionEvent) {
        table.getItems().clear();
        ArrayList<ArrayList<String>> list = database.getBookData();
        String keyword = searchBooks.getText().toLowerCase();

            for (ArrayList<String> object : list) {
                if((object.get(0).toLowerCase().indexOf(keyword)) > -1){
                    tableList.add(new bookDatabase(object.get(0), object.get(1), object.get(2), Integer.parseInt(object.get(3)), object.get(4), object.get(5), Integer.parseInt(object.get(6))));
                }
                else if((object.get(1).toLowerCase().indexOf(keyword)) > -1){
                    tableList.add(new bookDatabase(object.get(0), object.get(1), object.get(2), Integer.parseInt(object.get(3)), object.get(4), object.get(5), Integer.parseInt(object.get(6))));
                }
                else if((object.get(2).toLowerCase().indexOf(keyword)) > -1){
                    tableList.add(new bookDatabase(object.get(0), object.get(1), object.get(2), Integer.parseInt(object.get(3)), object.get(4), object.get(5), Integer.parseInt(object.get(6))));
                }
                else if((object.get(3).toLowerCase().indexOf(keyword)) > -1){
                    tableList.add(new bookDatabase(object.get(0), object.get(1), object.get(2), Integer.parseInt(object.get(3)), object.get(4), object.get(5), Integer.parseInt(object.get(6))));
                }
                else if((object.get(4).toLowerCase().indexOf(keyword)) > -1){
                    tableList.add(new bookDatabase(object.get(0), object.get(1), object.get(2), Integer.parseInt(object.get(3)), object.get(4), object.get(5), Integer.parseInt(object.get(6))));
                }
                else if((object.get(5).toLowerCase().indexOf(keyword)) > -1){
                    tableList.add(new bookDatabase(object.get(0), object.get(1), object.get(2), Integer.parseInt(object.get(3)), object.get(4), object.get(5), Integer.parseInt(object.get(6))));
                }
                else if((object.get(6).toLowerCase().indexOf(keyword)) > -1){
                    tableList.add(new bookDatabase(object.get(0), object.get(1), object.get(2), Integer.parseInt(object.get(3)), object.get(4), object.get(5), Integer.parseInt(object.get(6))));

                }
            }

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

            genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

            formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));

            availableColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));


            table.setItems(tableList);


        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = RIT_LibraryDatabase.getInstance();
        database.connect();

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
    public void loadAdd(ActionEvent actionEvent) {
        loadWindow("hello-view.fxml","Add Books");
    }

    public void loadUser(ActionEvent actionEvent) {
        loadWindow("addUser.fxml","Add User");
    }

    public void getRowItem(MouseEvent mouseEvent) {
        bookDatabase list1 = table.getSelectionModel().getSelectedItem();
        if(list1 != null){
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader();
            try {
                loader.setLocation(getClass().getResource("selectedBook.fxml"));
                 parent = loader.load();
                SelectedBook book = loader.getController();
                book.setBook(list1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("See more details about the selected book");

            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

}
