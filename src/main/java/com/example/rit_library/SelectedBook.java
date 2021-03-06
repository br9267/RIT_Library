package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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
    Review review;
    public void setBook(bookDatabase book) {
        this.book = book;
        review = new Review(book.getId());
        bookcreate = new Book(book.getId());
        bookcreate.fetch();
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        isbnLabel.setText(String.valueOf(book.getISBN()));
        genreLabel.setText(book.getGenre());
        formatLabel.setText(book.getFormat());
        courseLabel.setText(bookcreate.getCourse());
        publisherLabel.setText(bookcreate.getPublisher());
        yearLabel.setText(bookcreate.getYear());
        descriptionLabel.setText(bookcreate.getDescription());
        stateLabel.setText(bookcreate.getState());
        if(review.fetch()){
            gradeLabel.setText(review.getReview_grade());
            feedbackLabel.setText(review.getUser_feedback());
            recommendationLabel.setText(review.getUser_recommendation());
        }
        else{
            gradeLabel.setText("Currently did not receive any grades");
            feedbackLabel.setText("No feedback");
            recommendationLabel.setText("Not enough information");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    CurrentUser user = CurrentUser.getInstance();
    this.user = user.getUser();

    }

    public void addToFavorites(ActionEvent actionEvent) {
        Favorites favoriteBook = new Favorites(book.getId(), user.getId());
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
                   book.setAvailability(0);
            }
            else{
                if(time.testTime()){
                    Book_on_Loan loan = new Book_on_Loan(user.getId(),book.getId(),time.getDate());
                    Book bookObject = new Book(book.getId());
                    loan.setBookOnLoan();
                    bookObject.updateWhenBorrow();
                    book.setAvailability(0);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please reserve the book during working hours (8:00-16:00)");
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
        Parent parent = null;
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("ReviewBook.fxml"));
            parent = loader.load();
            ReviewBook review1 = loader.getController();
            loader.setController(review1);
            review1.setReview(review);


        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Review Book");

        stage.setScene(new Scene(parent));
        stage.show();

    }
}
