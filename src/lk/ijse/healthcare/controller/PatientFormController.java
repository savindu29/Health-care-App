package lk.ijse.healthcare.controller;

import com.sun.media.sound.AlawCodec;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import lk.ijse.healthcare.bo.BoFactory;
import lk.ijse.healthcare.bo.BoTypes;
import lk.ijse.healthcare.bo.custom.PatientBo;
import lk.ijse.healthcare.dao.custom.impl.PatientDaoImpl;

import lk.ijse.healthcare.dto.PatientDto;
import lk.ijse.healthcare.entity.Patient;
import lk.ijse.healthcare.view.tm.PatientTm;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Optional;

public class PatientFormController {
    public AnchorPane patientFormContext;
    public TextField txtName;
    public TextField txtSearch;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtId;
    public TableView tblPatientView;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;
    public Button savePatientBtn;
    private String sText= "";
   private PatientBo bo = BoFactory.getInstance().getBo(BoTypes.PATIENT);
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    searchData(sText);
    tblPatientView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if(newValue!=null){
            setData((PatientTm) newValue);

        }
    });
    txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        sText=newValue;
        searchData(newValue);
    });
    }

    private void setData(PatientTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        savePatientBtn.setText("Update Patient");
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
        PatientDto dto =new PatientDto(txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText());
        if(savePatientBtn.getText().equalsIgnoreCase("Save Patient")){
        try {

            if(bo.savePatient(dto)){
                clear();
                new Alert(Alert.AlertType.INFORMATION, "Patient Saved!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!..").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Try again..!!");
        }

        }else{
            try {

                if(bo.updatePatient(dto)){
                    clear();
                    new Alert(Alert.AlertType.INFORMATION,"Update Patient Successfully!..");
                }else {
                    new Alert(Alert.AlertType.WARNING, "Try Again!..").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        searchData(sText);

    }

    private void searchData(String text) {
        String searchText = "%"+text+"%";
        ObservableList<PatientTm>  tmList = FXCollections.observableArrayList();
        try {
            for (PatientDto dto:bo.searchPatients(searchText))
            {
                Button btn = new Button("Delete");
                tmList.add(new PatientTm(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getContact(),btn));
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure ?",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get()==ButtonType.YES){
                        try {

                            if(bo.deletePatient(dto.getId())){
                                searchData(searchText);
                                new Alert(Alert.AlertType.INFORMATION,
                                        "Patient Deleted!..").show();
                            }else{
                                new Alert(Alert.AlertType.WARNING,
                                        "Try Again!..").show();
                            }
                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR,
                                    "Error..").show();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblPatientView.setItems(tmList);

    }

    private void clear() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    public void newPatientOnAction(ActionEvent actionEvent) {
    clear();
    savePatientBtn.setText("Save Patient");
    }
}
