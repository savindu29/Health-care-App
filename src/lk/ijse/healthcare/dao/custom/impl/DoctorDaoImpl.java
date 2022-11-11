package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.CrudUtil;
import lk.ijse.healthcare.dao.custom.DoctorDao;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public boolean save(Doctor doctor) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("INSERT INTO doctor VALUES(?,?,?,?)",
               doctor.getdID(),doctor.getName(),doctor.getAddress(),doctor.getContact());
    }

    @Override
    public boolean update(Doctor doctor) throws SQLException, ClassNotFoundException {
        return  CrudUtil.execute("UPDATE doctor SET name=?, address=?, contact=? WHERE dId =?",doctor.getName(),doctor.getAddress(),doctor.getContact(),doctor.getdID());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM doctor WHERE dId=?",s);
    }

    @Override
    public Doctor get(String d) {
        return null;
    }

    @Override
    public ArrayList<Doctor> getAll() {
        return null;
    }

    @Override
    public ArrayList<Doctor> searchDoctors(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%"+text+"%";


        ResultSet rst = CrudUtil.execute("SELECT * FROM doctor WHERE name LIKE  ? || address LIKE ? || contact LIKE ? ||  dId LIKE ? ",searchText,searchText,searchText ,searchText);
        ArrayList<Doctor> doctors = new ArrayList<>();
        while(rst.next()){
            doctors.add(new Doctor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return doctors;
    }
}
