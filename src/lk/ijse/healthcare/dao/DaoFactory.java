package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.dao.custom.impl.AppointmentDaoImpl;
import lk.ijse.healthcare.dao.custom.impl.DoctorDaoImpl;
import lk.ijse.healthcare.dao.custom.impl.PatientDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return daoFactory==null?daoFactory=new DaoFactory():daoFactory;
    }
    public <T> T getDao(DaoTypes types){
        switch (types){
            case DOCTOR:
                return (T) new DoctorDaoImpl();
            case PATIENT:

                return (T) new PatientDaoImpl();
            case APPOINTMENT:
                return (T) new AppointmentDaoImpl();
            default:
                return null;
        }
    }
}
