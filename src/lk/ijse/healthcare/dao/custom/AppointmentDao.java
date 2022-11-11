package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.entity.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDao extends CrudDAO<Appointment,String> {
    public ArrayList<Appointment> searchAppointment(String text) throws SQLException, ClassNotFoundException;
}
