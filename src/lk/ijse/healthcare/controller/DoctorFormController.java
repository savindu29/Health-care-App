package lk.ijse.healthcare.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import lk.ijse.healthcare.bo.BoFactory;
import lk.ijse.healthcare.bo.BoTypes;
import lk.ijse.healthcare.bo.custom.DoctorBo;
import lk.ijse.healthcare.dao.custom.impl.DoctorDaoImpl;

import lk.ijse.healthcare.dto.DoctorDto;
import lk.ijse.healthcare.entity.Doctor;
import lk.ijse.healthcare.view.tm.DoctorTm;

import java.util.Optional;

public class DoctorFormController {
    public AnchorPane doctorFormContext;
    public TextField txtName;
    public TextField txtSearch;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtId;
    public Button saveDoctorBtn;
    public Button newDoctorBtn;
    public TableView<DoctorTm> tblDoctorsView;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;
    private String sText= "";

    private DoctorBo bo = BoFactory.getInstance().getBo(BoTypes.DOCTOR);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchData(sText);
        tblDoctorsView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData(newValue);
                    }

                });
        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
           sText=newValue;
            searchData(newValue);
        }));
    }

    private void setData(DoctorTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        saveDoctorBtn.setText("Update Doctor");
    }

    private void searchData(String text)  {
        String searchText = "%"+text+"%";
        ObservableList<DoctorTm> tmList =
                FXCollections.observableArrayList();
        try {
            for (DoctorDto dto : bo.searchDoctors(searchText)
            ) {

                Button btn = new Button("Delete");
                tmList.add(new DoctorTm(dto.getdID(), dto.getName()
                        , dto.getAddress(), dto.getContact(), btn));

                btn.setOnAction(event -> {

                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "are you sure?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            boolean isDeleted = bo.deleteDoctor(dto.getdID());
                            if (isDeleted) {
                                searchData(searchText);
                                new Alert(Alert.AlertType.INFORMATION,
                                        "Doctor Deleted!..").show();
                            } else {
                                System.out.println();
                                new Alert(Alert.AlertType.WARNING,
                                        "Try Again!..").show();
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                            new Alert(Alert.AlertType.ERROR,
                                    "Error..").show();
                        }
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblDoctorsView.setItems(tmList);
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        DoctorDto dto = new DoctorDto(txtId.getText(), txtName.getText(),
                txtAddress.getText(), txtContact.getText());


            if (saveDoctorBtn.getText().equalsIgnoreCase("Save Doctor")) {
                try {
                    boolean isSaved =bo.saveDoctor(dto);
                    if (isSaved) {
                        clear();
                        new Alert(Alert.AlertType.INFORMATION, "Doctor Saved!..").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!..").show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Try Again!..").show();
                }
            } else {
                try {
                    boolean isUpdated = bo.updateDoctor(dto);
                    if (isUpdated) {
                        clear();
                        new Alert(Alert.AlertType.INFORMATION, "Doctor Updated!..").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!..").show();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    new Alert(Alert.AlertType.ERROR, "Try Again!..").show();
                }
            }


        searchData(sText);

    }

    public void newDoctorOnAction(ActionEvent actionEvent) {

            saveDoctorBtn.setText("Save Doctor");
            tblDoctorsView.refresh();
            clear();

    }
    private void clear() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();

    }
}
