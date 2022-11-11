package lk.ijse.healthcare.view.tm;

import javafx.scene.control.Button;

import java.awt.*;

public class AppointmentTm {
    private String appointmentNumber;
    private String doctorId;
    private String patientId;
    private String date;



    private Button btn;

    public AppointmentTm(String appointmentNumber, String doctorId, String patientId, String date,Button btn) {
        this.appointmentNumber = appointmentNumber;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.btn=btn;
    }

    public AppointmentTm() {
    }
    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
