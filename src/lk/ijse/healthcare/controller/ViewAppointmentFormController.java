package lk.ijse.healthcare.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.healthcare.bo.BoFactory;
import lk.ijse.healthcare.bo.BoTypes;
import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.bo.custom.DoctorBo;
import lk.ijse.healthcare.dto.AppointmentDto;
import lk.ijse.healthcare.dto.DoctorDto;
import lk.ijse.healthcare.entity.Appointment;
import lk.ijse.healthcare.view.tm.AppointmentTm;
import lk.ijse.healthcare.view.tm.DoctorTm;

import java.util.Optional;

public class ViewAppointmentFormController {
    public AnchorPane ViewAppointmentFormContext;
    public TableView tblAppointments;
    public TableColumn colNumber;
    public TableColumn colDoctorId;
    public TableColumn colPatientId;
    public TableColumn colDate;
    public TableColumn colOption;
    private AppointmentBo bo = BoFactory.getInstance().getBo(BoTypes.APPOINTMENT);

    public void initialize(){
        colNumber.setCellValueFactory(new PropertyValueFactory<>("appointmentNumber"));
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        searchData("");

    }
    private void searchData(String text)  {

        ObservableList<AppointmentTm> tmList =
                FXCollections.observableArrayList();
        try {
            for (AppointmentDto dto : bo.searchAppointment(text)
            ) {

                Button btn = new Button("Delete");
                tmList.add(new AppointmentTm(dto.getAppointmentNumber(), dto.getDoctorId()
                        , dto.getPatientId(), dto.getDate(), btn));

                btn.setOnAction(event -> {

                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "are you sure?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            boolean isDeleted = bo.deleteAppointment(dto.getAppointmentNumber());
                            if (isDeleted) {
                                searchData(text);
                                new Alert(Alert.AlertType.INFORMATION,
                                        "Appointment Deleted!..").show();
                            } else {
                                System.out.println();
                                new Alert(Alert.AlertType.WARNING,
                                        "Try Again!..").show();
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                            new Alert(Alert.AlertType.ERROR,
                                    "Error..").show();
                        }
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblAppointments.setItems(tmList);
    }
}
