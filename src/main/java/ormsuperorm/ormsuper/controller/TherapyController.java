package ormsuperorm.ormsuper.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ormsuperorm.ormsuper.bo.BOFactory;
import ormsuperorm.ormsuper.bo.custom.TherapistBO;
import ormsuperorm.ormsuper.dto.TherapistDTO;
import ormsuperorm.ormsuper.tm.TherapyTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyController implements Initializable {

    TherapistBO therapistBO = (TherapistBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.THERAPY);

    @FXML
    private TableColumn<TherapyTM, String> emailCol;

    @FXML
    private TableColumn<TherapyTM, String> proCol;

    @FXML
    private TableColumn<TherapyTM, String> tIdCol;

    @FXML
    private TableColumn<TherapyTM, String> tNmeCol;

    @FXML
    private Label proNameLbl;

    @FXML
    private TextField emailTtx;

    @FXML
    private TextField protxt;

    @FXML
    private TextField tIdTxt;

    @FXML
    private TextField tNmTxt;

    @FXML
    private TableView<TherapyTM> tblTheapiest;

    @FXML
    private Button tSave;

    @FXML
    private Button tUpdte;

    @FXML
    private Button tDelete;


    @FXML
    void TBtnDelete(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String ID = tIdTxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = therapistBO.delete(ID);
            if (isDelete) {
                LoadNextID();
                loadTableData();
                new Alert(Alert.AlertType.INFORMATION, "Therapy deleted...!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Therapy...!").show();

            }
        }
    }

    @FXML
    void tBtnSVE(ActionEvent event) {
        String therapistId = tIdTxt.getText();
        String therapistName = tNmTxt.getText();
        String profession = protxt.getText();
        String email = emailTtx.getText();

        try {
            boolean isSaved = therapistBO.save(new TherapistDTO(therapistId,therapistName,profession,email));
            if(isSaved){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Therapy Saved SUCCESSFULLY 😎").show();

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
    void tBtnUpdte(ActionEvent event) {
        String therapistId = tIdTxt.getText();
        String therapistName = tNmTxt.getText();
        String profession = protxt.getText();
        String email = emailTtx.getText();

        try {
            boolean isSaved = therapistBO.update(new TherapistDTO(therapistId,therapistName,profession,email));
            if(isSaved){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Therapy update SUCCESSFULLY 😎").show();

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
        tIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        tNmeCol.setCellValueFactory(new PropertyValueFactory<>("therapistName"));
        proCol.setCellValueFactory(new PropertyValueFactory<>("profession"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        try {
           refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadNextID() throws SQLException, IOException {
        String nextID = therapistBO.getNextId();
        tIdTxt.setText(nextID);
    }
    private void loadTableData() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<TherapistDTO> therapistDTOS = (ArrayList<TherapistDTO>) therapistBO.getAll();
        ObservableList<TherapyTM> therapistTMS = FXCollections.observableArrayList();

        for (TherapistDTO therapistDTO : therapistDTOS) {
            TherapyTM therapistTM = new TherapyTM(
                    therapistDTO.getTherapistId(),
                    therapistDTO.getTherapistName(),
                    therapistDTO.getProfession(),
                    therapistDTO.getEmail()

            );
            therapistTMS.add(therapistTM);
        }
        tblTheapiest.setItems(therapistTMS);
    }
    void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        LoadNextID();
        loadTableData();

        tSave.setDisable(true);
        tDelete.setDisable(false);
        tUpdte.setDisable(true);

        tIdTxt.setText("");
        tNmTxt.setText("");
        protxt.setText("");
        emailTtx.setText("");

    }

    @FXML
    void proIdComboAcc(ActionEvent event) {

    }

}


///reset button ekai

///therapistTMS

/// on click ekai hdnna one