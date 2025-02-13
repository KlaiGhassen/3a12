package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Students;
import services.StudentsService;

import java.util.List;


public class ShouwStudents {
    StudentsService ss;

    public ShouwStudents() {
        ss = new StudentsService();

    }

    @FXML
    private TableColumn<Students, Integer> ageCol;

    @FXML
    private TableColumn<Students, String> firstNameCol;

    @FXML
    private TableColumn<Students, String> lastNameCol;

    @FXML
    private TableView<Students> studentsTab;


    @FXML
    void initialize() {
        try {
            List<Students> studentsList = this.ss.getAll();
            ObservableList<Students> studentsListObservable = FXCollections.observableArrayList(studentsList);
            studentsTab.setItems(studentsListObservable);
            ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

}
