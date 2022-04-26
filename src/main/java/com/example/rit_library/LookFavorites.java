package com.example.rit_library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LookFavorites implements Initializable {
    public Button removeButton;
    public Button cancelButton;
    public RIT_LibraryDatabase database;
    public Favorites favorite;
    public TableView TableViewFavorite;
    public TableColumn<Book, String> titleColumn;
    public TableColumn<Book, Integer> ISBNColumn;
    public TableColumn<Book, String> PublisherColumn;
    public TableColumn<Book, String> CourseColumn;
    ObservableList<Book> items = FXCollections.observableArrayList();
    public User user;
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void removeFavorite(ActionEvent actionEvent) {
        Book list1 = (Book) TableViewFavorite.getSelectionModel().getSelectedItem();
        if(list1 != null){
            Favorites favoriteObject = new Favorites(String.valueOf(list1.getBook_id()),user.getId());
            favoriteObject.removeFavorites();
            TableViewFavorite.getItems().remove(list1);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please choose which book you wish to remove from your favorites");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setFavorites(Favorites favorite) {
        this.favorite = favorite;
        CurrentUser userCurrent = CurrentUser.getInstance();
        this.user = userCurrent.getUser();
        favorite.setUser_id(user.getId());
        ArrayList<ArrayList<String>> data = favorite.getAllFavorites();
        for (ArrayList<String> object : data){
            Book book = new Book(object.get(1));
            book.fetch();
            items.add(book);
        }
        TableViewFavorite.setItems(items);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        CourseColumn.setCellValueFactory(new PropertyValueFactory<>("Course"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<>("Publisher"));


    }

}
