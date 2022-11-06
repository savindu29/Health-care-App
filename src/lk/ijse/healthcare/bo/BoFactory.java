package lk.ijse.healthcare.bo;

import lk.ijse.healthcare.bo.custom.impl.DoctorBoImpl;
import lk.ijse.healthcare.bo.custom.impl.PatientBoImpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}
    public static BoFactory getInstance(){
        return boFactory==null?boFactory=new BoFactory():boFactory;
    }
    public <T> T getBo(BoTypes type){
        switch (type){
            case DOCTOR:
                return (T) new DoctorBoImpl();
            case PATIENT:
                return (T) new PatientBoImpl();
            default: return null;
        }
    }
}
