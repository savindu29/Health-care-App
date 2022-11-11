package lk.ijse.healthcare.bo.custom.impl;


import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.bo.custom.DoctorBo;
import lk.ijse.healthcare.dao.DaoFactory;
import lk.ijse.healthcare.dao.DaoTypes;
import lk.ijse.healthcare.dao.custom.AppointmentDao;
import lk.ijse.healthcare.dao.custom.DoctorDao;
import lk.ijse.healthcare.dto.AppointmentDto;
import lk.ijse.healthcare.dto.DoctorDto;
import lk.ijse.healthcare.entity.Appointment;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBoImpl implements AppointmentBo {

   private AppointmentDao dao = DaoFactory.getInstance().getDao(DaoTypes.APPOINTMENT);
    @Override
    public boolean saveAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Appointment(
                dto.getAppointmentNumber(),
                dto.getDoctorId(),
                dto.getPatientId(),
                dto.getDate()
        ));
    }

    @Override
    public boolean updateAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
       return dao.update(new Appointment(
               dto.getAppointmentNumber(),
               dto.getDoctorId(),
               dto.getPatientId(),
               dto.getDate()));
    }

    @Override
    public boolean deleteAppointment(String id) throws SQLException, ClassNotFoundException {
       return dao.delete(id);
    }

    @Override
    public ArrayList<AppointmentDto> searchAppointment(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> entities = dao.searchAppointment(id);
        ArrayList<AppointmentDto> dtoList = new ArrayList<>();
        for (Appointment a:entities) {
            dtoList.add(new AppointmentDto(
                    a.getAppointmentNumber(),
                    a.getDoctorId(),
                    a.getPatientId(),
                    a.getDate()
            ));

        }
        return dtoList;
    }
}
