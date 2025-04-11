package ormsuperorm.ormsuper.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ormsuperorm.ormsuper.bo.BOFactory;
import ormsuperorm.ormsuper.bo.custom.PatientBO;
import ormsuperorm.ormsuper.bo.custom.UserBO;
import ormsuperorm.ormsuper.dto.PatientDTO;
import ormsuperorm.ormsuper.dto.UserDTO;
import ormsuperorm.ormsuper.tm.PatientTM;
import ormsuperorm.ormsuper.tm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    PatientBO patientBO = (PatientBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PATIENT);

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> medicalCol;

    @FXML
    private TableColumn<?, ?> patAgeCol;

    @FXML
    private TextField patAgeTxt;

    @FXML
    private TableColumn<?, ?> patContactCol;

    @FXML
    private TextField patContactTxt;

    @FXML
    private TableColumn<?, ?> patIdCol;

    @FXML
    private TextField patIdTxt;

    @FXML
    private TextField patMedicalHistoryTxt;

    @FXML
    private TableColumn<?, ?> patNameCol;

    @FXML
    private TextField patNameTxt;

    @FXML
    private TableView<?> patientTable;

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnSave(ActionEvent event) {
        String patId = patIdTxt.getText();
        String patName = patNameTxt.getText();
        int patAge = Integer.parseInt(patAgeTxt.getText());
        int patContact = Integer.parseInt(patContactTxt.getText());
        String medicalHistory = patMedicalHistoryTxt.getText();


        try {
            boolean isSaved = patientBO.save(new UserDTO(patId,patName,patAge,patContact,medicalHistory));
            if(isSaved){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"User Save SUCCESSFULLY 😎").show();

            }
            else {
                new Alert(Alert.AlertType.ERROR,"PLEASE TRY AGAIN 😥").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"duplicate Id");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        String patId = patIdTxt.getText();
        String patName = patNameTxt.getText();
        int patAge = Integer.parseInt(patAgeTxt.getText());
        int patContact = Integer.parseInt(patContactTxt.getText());
        String medicalHistory = patMedicalHistoryTxt.getText();


        try {
            boolean isSaved = patientBO.update(new UserDTO(patId,patName,patAge,patContact,medicalHistory));
            if(isSaved){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"User Save SUCCESSFULLY 😎").show();

            }
            else {
                new Alert(Alert.AlertType.ERROR,"PLEASE TRY AGAIN 😥").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"duplicate Id");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patIdCol.setCellValueFactory(new PropertyValueFactory<>("patId"));
        patNameCol.setCellValueFactory(new PropertyValueFactory<>("patName"));
        patAgeCol.setCellValueFactory(new PropertyValueFactory<>("patAge"));
        patContactCol.setCellValueFactory(new PropertyValueFactory<>("patContact"));
        medicalCol.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadNextID() throws SQLException, IOException {
        String nextID = patientBO.getNextId();
        patIdTxt.setText(nextID);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<PatientDTO> patientDTOS = (ArrayList<PatientDTO>) patientBO.getAll();
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for (PatientDTO patientDTO : patientDTOS) {
            PatientTM patientTM = new PatientTM(
                    patientDTO.getPatId(),
                    patientDTO.getPatName(),
                    patientDTO.getPatAge(),
                    patientDTO.getPatContact(),
                    patientDTO.getMedicalHistory()
            );
            patientTMS.add(patientTM);
        }
        patientTable.setItems(patientTMS);

    }

    void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        LoadNextID();
        loadTableData();

        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);

        patIdTxt.setText("");
        patNameTxt.setText("");
        patAgeTxt.setText("");
        patContactTxt.setText("");
        patMedicalHistoryTxt.setText("");

    }
}
