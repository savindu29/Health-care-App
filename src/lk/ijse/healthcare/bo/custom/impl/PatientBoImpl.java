package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.PatientBo;
import lk.ijse.healthcare.dao.DaoFactory;
import lk.ijse.healthcare.dao.DaoTypes;
import lk.ijse.healthcare.dao.custom.PatientDao;
import lk.ijse.healthcare.dto.PatientDto;
import lk.ijse.healthcare.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public class PatientBoImpl implements PatientBo {

   private PatientDao dao = DaoFactory.getInstance().getDao(DaoTypes.PATIENT);
    @Override
    public boolean savePatient(PatientDto dto) throws SQLException, ClassNotFoundException {
       return dao.save(new Patient(
               dto.getId(),
               dto.getName(),
               dto.getAddress(),
               dto.getContact()
       ));
    }

    @Override
    public boolean updatePatient(PatientDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Patient(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact()
        ));
    }

    @Override
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<PatientDto> searchPatients(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Patient> entities = dao.searchPatients(id);
        ArrayList<PatientDto> dtoList = new ArrayList<>();
        for (Patient p:entities)
        {
        dtoList.add(new PatientDto(
                p.getId(),
                p.getName(),
                p.getAddress(),
                p.getContact()
        ));
        }
        return dtoList;
    }
}
