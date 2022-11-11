package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.dto.AppointmentDto;
import lk.ijse.healthcare.dto.DoctorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBo {
    public boolean saveAppointment (AppointmentDto dto) throws SQLException, ClassNotFoundException;
    public boolean updateAppointment (AppointmentDto dto) throws SQLException, ClassNotFoundException;
    public boolean deleteAppointment (String id) throws SQLException, ClassNotFoundException;
    public ArrayList<AppointmentDto> searchAppointment (String id) throws SQLException, ClassNotFoundException;
}
