package lk.ijse.healthcare.dto;

public class Appointment {
    String doctorId;
    String patientId;
    String date;

    public Appointment(String doctorId, String patientId, String date) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
