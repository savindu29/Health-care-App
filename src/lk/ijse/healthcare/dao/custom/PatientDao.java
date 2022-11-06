package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientDao extends CrudDAO<Patient,String> {
    public ArrayList<Patient> searchPatients(String text) throws SQLException, ClassNotFoundException;
}
