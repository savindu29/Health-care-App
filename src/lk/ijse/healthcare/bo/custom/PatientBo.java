package lk.ijse.healthcare.bo.custom;


import lk.ijse.healthcare.dto.PatientDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientBo {
    public boolean savePatient (PatientDto dto) throws SQLException, ClassNotFoundException;
    public boolean updatePatient(PatientDto dto) throws SQLException, ClassNotFoundException;
    public boolean deletePatient (String id) throws SQLException, ClassNotFoundException;
    public ArrayList<PatientDto> searchPatients (String id) throws SQLException, ClassNotFoundException;
}
