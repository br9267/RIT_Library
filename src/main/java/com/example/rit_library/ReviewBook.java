package com.example.rit_library;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReviewBook implements Initializable {
    public ComboBox gradeBook;
    public TextArea feedbackArea;
    public ComboBox recommendationBook;
    public Button reviewButton;
    public Button cancelButton;
    public Review review;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            gradeBook.getItems().addAll("1","2","3","4","5");
            recommendationBook.getItems().addAll("True", "False");
    }

    public void cancelReview(ActionEvent actionEvent) {
        Stage stage = (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setReview(Review review){
        this.review = review;
    }
    public void reviewBook(ActionEvent actionEvent) {
        if(feedbackArea.getText().trim().length() == 0 || gradeBook.getValue() == null || recommendationBook.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please fill out all of the fields");
            alert.showAndWait();
        }else {

            if(review.fetch()){
                review.setReview_grade(String.valueOf(gradeBook.getValue()));
                review.setUser_feedback(feedbackArea.getText());

                if(recommendationBook.getValue() == "true"){
                    review.setUser_recommendation("1");
                }
                else{
                    review.setUser_recommendation("0");
                }
                    review.update();
            }
            else{
                review.setReview_grade(String.valueOf(gradeBook.getValue()));
                review.setUser_feedback(feedbackArea.getText());

                if(recommendationBook.getValue() == "true"){
                    review.setUser_recommendation("1");
                }
                else{
                    review.setUser_recommendation("0");
                }
                    review.put();
            }
        }
    }
}
