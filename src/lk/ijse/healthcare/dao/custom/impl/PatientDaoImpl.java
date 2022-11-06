package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.CrudUtil;
import lk.ijse.healthcare.dao.custom.PatientDao;
import lk.ijse.healthcare.entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientDaoImpl implements PatientDao {
    @Override
    public boolean save(Patient patient) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO patient VALUES(?,?,?,?)",
                patient.getId(), patient.getName(), patient.getAddress(), patient.getContact());
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        return  CrudUtil.execute("UPDATE patient SET name=?, address=?, contact=? WHERE id =?", patient.getName(), patient.getAddress(), patient.getContact(), patient.getId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM patient WHERE id=?",s);
    }

    @Override
    public Patient get(String d) {
        return null;
    }

    @Override
    public ArrayList<Patient> getAll() {
        return null;
    }

    @Override
    public ArrayList<Patient> searchPatients(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%"+text+"%";


        ResultSet rst = CrudUtil.execute("SELECT * FROM patient WHERE name LIKE  ? || address LIKE ? || contact LIKE ? ||  id LIKE ? ",searchText,searchText,searchText ,searchText);
        ArrayList<Patient> patients = new ArrayList<>();
        while (rst.next()){
            patients.add(new Patient(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }

        return patients;
    }
}
