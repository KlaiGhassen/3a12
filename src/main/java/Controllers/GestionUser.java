package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Students;
import services.StudentsService;

import java.io.IOException;


public class GestionUser {
    StudentsService ss;

    public GestionUser() {
        this.ss = new StudentsService();
    }

    @FXML
    private TextField age;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    void saveStudent(ActionEvent event) {
        Students s = new Students(Integer.parseInt(this.age.getText()), this.firstName.getText(), this.lastName.getText());
        try {
            this.ss.create(s);
            reset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Student created");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void showStudents(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowStudents.fxml"));
        try {
            Parent root = loader.load();
            age.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void reset() {
        this.age.clear();
        this.firstName.clear();
        this.lastName.clear();
    }
}
