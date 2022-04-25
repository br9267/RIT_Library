package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectedBook implements Initializable {
    public Button favoriteButton;
    public Button reviewButton;
    public Button borrowButton;
    public Label titleLabel;
    public Label authorLabel;
    public Label courseLabel;
    public Label yearLabel;
    public Label descriptionLabel;
    public Label formatLabel;
    public Label isbnLabel;
    public Label genreLabel;
    public Label stateLabel;
    public Label gradeLabel;
    public Label feedbackLabel;
    public Label recommendationLabel;
    public Label publisherLabel;
    bookDatabase book;
    User user;
    Book bookcreate;
    public void setBook(bookDatabase book) {
        this.book = book;
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        isbnLabel.setText(String.valueOf(book.getISBN()));
        genreLabel.setText(book.getGenre());
        formatLabel.setText(book.getFormat());
        bookcreate = new Book(book.getId());
        bookcreate.fetch();
        courseLabel.setText(bookcreate.getCourse());
        publisherLabel.setText(bookcreate.getPublisher());
        yearLabel.setText(bookcreate.getYear());
        descriptionLabel.setText(bookcreate.getDescription());
        stateLabel.setText(bookcreate.getState());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    CurrentUser user = CurrentUser.getInstance();
    this.user = user.getUser();

    }

    public void addToFavorites(ActionEvent actionEvent) {
        Favorites favoriteBook = new Favorites(book.getId(), user.getId());
        System.out.println(book.getTitle());
        System.out.println(user.getId());
        if(favoriteBook.setFavorite()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "This is already in your favorites");
            alert.showAndWait();
        }
        else{
            favoriteBook.saveFavorite();
        }
    }

    public void borrowBook(ActionEvent actionEvent) {
        CurrentTime time = new CurrentTime();
        if(book.getAvailability()){
            System.out.println(book.getFormat());
            if(book.getFormat().equalsIgnoreCase("PDF")){
                   Book_on_Loan loan = new Book_on_Loan(user.getId(),book.getId(),time.getDate());
                   Book bookObject = new Book(book.getId());
                   loan.setBookOnLoan();
                   bookObject.updateWhenBorrow();
            }
            else{
                if(time.testTime()){
                    Book_on_Loan loan = new Book_on_Loan(user.getId(),book.getId(),time.getDate());
                    Book bookObject = new Book(book.getId());
                    loan.setBookOnLoan();
                    bookObject.updateWhenBorrow();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please reserve the book during working hours");
                    alert.showAndWait();
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Book is currently borrowed unfortunately :(");
            alert.showAndWait();
        }
    }

    public void reviewBook(ActionEvent actionEvent) {
    }
}
