package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.dto.DoctorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorBo {
    public boolean saveDoctor (DoctorDto dto) throws SQLException, ClassNotFoundException;
    public boolean updateDoctor (DoctorDto dto) throws SQLException, ClassNotFoundException;
    public boolean deleteDoctor (String id) throws SQLException, ClassNotFoundException;
    public ArrayList<DoctorDto> searchDoctors (String id) throws SQLException, ClassNotFoundException;
}
