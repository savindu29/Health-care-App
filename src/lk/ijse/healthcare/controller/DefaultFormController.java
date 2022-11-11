package lk.ijse.healthcare.controller;

import javafx.scene.control.Label;
import lk.ijse.healthcare.bo.BoFactory;
import lk.ijse.healthcare.bo.BoTypes;
import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.bo.custom.DoctorBo;
import lk.ijse.healthcare.bo.custom.PatientBo;

import java.sql.SQLException;

public class DefaultFormController {

    public Label lblNumberOfAppointments;
    public Label lblNumberOfDoctors;
    public Label lblNumberOdPatients;
    private DoctorBo boD = BoFactory.getInstance().getBo(BoTypes.DOCTOR);
    private PatientBo boP= BoFactory.getInstance().getBo(BoTypes.PATIENT);
    private AppointmentBo boA = BoFactory.getInstance().getBo(BoTypes.APPOINTMENT);
    public void initialize(){
        try {
            lblNumberOdPatients.setText(boP.searchPatients("").size()+"");
            lblNumberOfDoctors.setText(boD.searchDoctors("").size()+"");
            lblNumberOfAppointments.setText(boA.searchAppointment("").size()+"");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
