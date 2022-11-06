package lk.ijse.healthcare.dto;

public class DoctorDto {
    private String dID;
    private String Name;
    private String Address;
    private String Contact;

    public DoctorDto() {
    }

    public DoctorDto(String dID, String name, String address, String contact) {
        this.dID = dID;
        Name = name;
        Address = address;
        Contact = contact;
    }
    public String getdID() {
        return dID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
