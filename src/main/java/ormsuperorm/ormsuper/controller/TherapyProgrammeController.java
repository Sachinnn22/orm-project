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
import ormsuperorm.ormsuper.bo.custom.TherapyProgrammeBO;
import ormsuperorm.ormsuper.dto.TherapyProgramDTO;
import ormsuperorm.ormsuper.tm.TherapyProgrammeTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyProgrammeController implements Initializable {

    TherapyProgrammeBO therapyProgrammeBO = (TherapyProgrammeBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.THERAPYPROGRAMME);
    TherapistBO therapistBO = (TherapistBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.THERAPY);

    @FXML
    private TableColumn<TherapyProgrammeTM, String> proIdCol, proNameCol, discriptionCol, therIdCol;

    @FXML
    private TableColumn<TherapyProgrammeTM, Double> proFeeCol;

    @FXML
    private TableColumn<TherapyProgrammeTM, Integer> durationCol;

    @FXML
    private TextField progrmeIdTxt, proNmeTxt, proFeeTxt, durationTxt, disTxt;

    @FXML
    private TableView<TherapyProgrammeTM> tblTherapyProgramme;

    @FXML
    private ComboBox<String> therIdCombo;

    @FXML
    private Button tpbtnSave, tpbtnUpdate, tpbtnDelete;

    @FXML
    void proBtnDelete(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String ID = progrmeIdTxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDelete = therapyProgrammeBO.delete(ID);
            if (isDelete) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Therapy Programme deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Therapy Programme...!").show();
            }
        }
    }

    @FXML
    void proBtnSVE(ActionEvent event) {
        String id = progrmeIdTxt.getText();
        String name = proNmeTxt.getText();
        String description = disTxt.getText();
        String therapyId = therIdCombo.getValue();

        try {
            double fee = Double.parseDouble(proFeeTxt.getText());
            int duration = Integer.parseInt(durationTxt.getText());
            boolean isSaved = therapyProgrammeBO.save(new TherapyProgramDTO(id, name, fee, duration, description));

            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Therapy Programme Saved SUCCESSFULLY 😎").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "PLEASE TRY AGAIN 😥").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Duplicate ID").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void proBtnUpdte(ActionEvent event) {
        String id = progrmeIdTxt.getText();
        String name = proNmeTxt.getText();
        String description = disTxt.getText();
        String therapyId = therIdCombo.getValue();

        try {
            double fee = Double.parseDouble(proFeeTxt.getText());
            int duration = Integer.parseInt(durationTxt.getText());
            boolean isUpdated = therapyProgrammeBO.update(new TherapyProgramDTO(id, name, fee, duration, description));

            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Therapy Programme Updated SUCCESSFULLY 😎").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "PLEASE TRY AGAIN 😥").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Duplicate ID").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        proIdCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgrammeId"));
        proNameCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgrammeName"));
        proFeeCol.setCellValueFactory(new PropertyValueFactory<>("fee"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        discriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        therIdCol.setCellValueFactory(new PropertyValueFactory<>("therapyId"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<TherapyProgrammeTM> programmeTMS = FXCollections.observableArrayList();
        for (TherapyProgramDTO dto : therapyProgrammeBO.getAll()) {
            programmeTMS.add(new TherapyProgrammeTM(dto.getProgrammeId(), dto.getProgrammeName(), dto.getFee(), dto.getDuration(), dto.getDescription()));
        }
        tblTherapyProgramme.setItems(programmeTMS);
    }

    void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        loadTableData();
        progrmeIdTxt.clear();
        proNmeTxt.clear();
        proFeeTxt.clear();
        durationTxt.clear();
        disTxt.clear();
        therIdCombo.setValue(null);
    }

    public void therIdComboAcc(ActionEvent actionEvent) {
    }
}
