package lk.ijse.healthcare.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.healthcare.bo.BoFactory;
import lk.ijse.healthcare.bo.BoTypes;
import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.bo.custom.DoctorBo;
import lk.ijse.healthcare.bo.custom.PatientBo;
import lk.ijse.healthcare.dto.AppointmentDto;
import lk.ijse.healthcare.dto.DoctorDto;
import lk.ijse.healthcare.dto.PatientDto;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.SQLException;

public class AppointmentFormController {
    public AnchorPane appointmentFormContext;
    public TextField txtDoctor;
    public Button btnNewAppoin;
    public TextField txtPatient;
    public DatePicker datePicker;
    public ComboBox cmbPatient;
    public ComboBox cmbDoctor;
    public TextField txtPatientAddress;
    public TextField txtPatientTel;
    public TextField txtDoctorAddress;
    public TextField txtDoctorTel;
    public TextField txtAppointmentNumber;
    private String sText= "";
    private DoctorBo boD = BoFactory.getInstance().getBo(BoTypes.DOCTOR);
    private AppointmentBo boA = BoFactory.getInstance().getBo(BoTypes.APPOINTMENT);
    private PatientBo boP = BoFactory.getInstance().getBo(BoTypes.PATIENT);
    public void initialize(){
        loadCodes("");

        cmbDoctor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setDoctorDetails();

            }
        });
        cmbPatient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setPatientDetails();

            }
        });
    }

    private void setDoctorDetails() {
        try {

            DoctorDto doctor = boD.searchDoctors(String.valueOf(cmbDoctor.getValue())).get(0);
            txtDoctor.setText(doctor.getName());
            txtDoctorAddress.setText(doctor.getAddress());
            txtDoctorTel.setText(doctor.getContact());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setPatientDetails() {
        try {

            PatientDto patient = boP.searchPatients(String.valueOf(cmbPatient.getValue())).get(0);
            txtPatient.setText(patient.getName());
            txtPatientAddress.setText(patient.getAddress());
            txtPatientTel.setText(patient.getContact());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadCodes(String text) {
        String searchText = "%"+text+"%";
        try {
            for (DoctorDto doctorDto: boD.searchDoctors(text)) {
                cmbDoctor.getItems().add(doctorDto.getdID());

            }
            for (PatientDto patientDto: boP.searchPatients(text)) {
                cmbPatient.getItems().add(patientDto.getId());

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
        AppointmentDto dto = new AppointmentDto(txtAppointmentNumber.getText(), (String) cmbDoctor.getSelectionModel().selectedItemProperty().getValue(),(String) cmbPatient.getSelectionModel().selectedItemProperty().getValue(),datePicker.getValue().toString());
        try {
            boolean isSaved =boA.saveAppointment(dto);
            if (isSaved) {
                clear();
                new Alert(Alert.AlertType.INFORMATION, "Placed Appointment!..").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!..").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Try Again!..").show();
        }
    }
    private void clear(){
        txtDoctorTel.clear();
        txtDoctor.clear();
        txtDoctorAddress.clear();
        txtPatientTel.clear();
        txtPatient.clear();
        txtPatientTel.clear();
        txtAppointmentNumber.clear();
    }
}
