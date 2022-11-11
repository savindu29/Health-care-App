package lk.ijse.healthcare.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {
    public AnchorPane dashboardContext;
    public Label lblDate;
    public AnchorPane DashboardformContext;


    public void initialize() throws IOException {
        loadForm("DefaultForm");
        setDateAndTime();
    }


    private void loadForm(String location) throws IOException {
        dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(
                FXMLLoader.load(
                        getClass().getResource("../view/"+location+".fxml")
                )
        );
    }
    public void setUI(String location) throws IOException {
        Stage stage = (Stage) DashboardformContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }

    public void doctorBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("DoctorForm");
    }
    public void newAppointmentBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("AppointmentForm");
    }
    public void viewAppointmentBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("ViewAppointmentForm");
    }


    public void patientBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("PatientForm");
    }

    public void dashBoardBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadForm("DefaultForm");
    }
    private void setDateAndTime(){
        // setTime
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    lblDate.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void signOutBtnOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");

    }
}
