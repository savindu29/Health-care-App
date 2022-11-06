package lk.ijse.healthcare.bo.custom.impl;


import lk.ijse.healthcare.bo.custom.DoctorBo;
import lk.ijse.healthcare.dao.DaoFactory;
import lk.ijse.healthcare.dao.DaoTypes;
import lk.ijse.healthcare.dao.custom.DoctorDao;

import lk.ijse.healthcare.dto.DoctorDto;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorBoImpl implements DoctorBo {

   private DoctorDao dao = DaoFactory.getInstance().getDao(DaoTypes.DOCTOR);
    @Override
    public boolean saveDoctor(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Doctor(
                dto.getdID(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact()
        ));
    }

    @Override
    public boolean updateDoctor(DoctorDto dto) throws SQLException, ClassNotFoundException {
       return dao.update(new Doctor(
               dto.getdID(),
               dto.getName(),
               dto.getAddress(),
               dto.getContact()));
    }

    @Override
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException {
       return dao.delete(id);
    }

    @Override
    public ArrayList<DoctorDto> searchDoctors(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Doctor> entities = dao.searchDoctors(id);
        ArrayList<DoctorDto> dtoList = new ArrayList<>();
        for (Doctor d:entities) {
            dtoList.add(new DoctorDto(
                    d.getdID(),
                    d.getName(),
                    d.getAddress(),
                    d.getContact()
            ));

        }
        return dtoList;
    }
}
