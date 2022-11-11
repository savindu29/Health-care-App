package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.CrudUtil;
import lk.ijse.healthcare.dao.custom.AppointmentDao;
import lk.ijse.healthcare.entity.Appointment;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentDaoImpl implements AppointmentDao {
    @Override
    public boolean save(Appointment appointment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO appointment VALUES(?,?,?,?)",
                appointment.getAppointmentNumber(),appointment.getDoctorId(),appointment.getPatientId(),appointment.getDate());
    }

    @Override
    public boolean update(Appointment appointment) throws SQLException, ClassNotFoundException {
        return  CrudUtil.execute("UPDATE appointment SET  doctor_id=?, patient_id=? date=? WHERE appointment_number =?",appointment.getDoctorId(),appointment.getPatientId(),appointment.getDate(),appointment.getAppointmentNumber());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM appointment WHERE appointment_number=?",s);
    }

    @Override
    public Appointment get(String d) {
        return null;
    }

    @Override
    public ArrayList<Appointment> getAll() {
        return null;
    }

    @Override
    public ArrayList<Appointment> searchAppointment(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%"+text+"%";


        ResultSet rst = CrudUtil.execute("SELECT * FROM appointment WHERE appointment_number LIKE  ? || doctor_id LIKE ? || patient_id LIKE ? ||  date LIKE ? ",searchText,searchText,searchText ,searchText);
        ArrayList<Appointment> appointments = new ArrayList<>();
        while(rst.next()){
            appointments.add(new Appointment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return appointments;
    }
}
