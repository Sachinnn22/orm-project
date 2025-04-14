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
import ormsuperorm.ormsuper.bo.custom.PaymentBO;
import ormsuperorm.ormsuper.dto.PatientDTO;
import ormsuperorm.ormsuper.dto.PaymentDTO;
import ormsuperorm.ormsuper.dto.UserDTO;
import ormsuperorm.ormsuper.entity.Payment;
import ormsuperorm.ormsuper.tm.PatientTM;
import ormsuperorm.ormsuper.tm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PAYMENT);


    @FXML
    private TableColumn<?, ?> balanceCol;

    @FXML
    private Button btnSve;

    @FXML
    private TableColumn<?, ?> cashCol;

    @FXML
    private ComboBox<?> comboPatId;

    @FXML
    private TableColumn<?, ?> conductorCol;

    @FXML
    private TableColumn<?, ?> patIdCol;

    @FXML
    private TableColumn<?, ?> payIdCol;

    @FXML
    private TableColumn<?, ?> payTypeCol;

    @FXML
    private Button slipGenBtn;

    @FXML
    private TableView<?> tnlPayment;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtCash;

    @FXML
    private TextField txtConductor;

    @FXML
    private TextField txtPayId;

    @FXML
    private TextField txtPayType;

    @FXML
    void btnSveOnAcc(ActionEvent event) {
        String payId = txtPayId.getText();
        String payMethod = txtPayType.getText();
        String patId = txtPayType.getText();
        String conductor = txtPayType.getText();
        double cash = Double.parseDouble(txtPayType.getText());
        double balance = Double.parseDouble(txtPayType.getText());

        try {
            boolean isSaved = paymentBO.save(new UserDTO(payId,payMethod,patId,conductor,cash,balance));
            if(isSaved){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Payment Save SUCCESSFULLY 😎").show();

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
    void patIdComboOnAcc(ActionEvent event) {

    }

    @FXML
    void slipGenOnAcc(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payIdCol.setCellValueFactory(new PropertyValueFactory<>("payId"));
        payTypeCol.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        patIdCol.setCellValueFactory(new PropertyValueFactory<>("patId"));
        conductorCol.setCellValueFactory(new PropertyValueFactory<>("conductor"));
        cashCol.setCellValueFactory(new PropertyValueFactory<>("cash"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadNextID() throws SQLException, IOException {
        String nextID = paymentBO.getNextId();
        txtPayId.setText(nextID);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<PaymentDTO> paymentDTOS = (ArrayList<PaymentDTO>) paymentBO.getAll();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getPaymentId(),
                    paymentDTO.getPaymentType(),
                    paymentDTO.getPatientId(),
                    paymentDTO.getConductor(),
                    paymentDTO.getCash(),
                    paymentDTO.getBalance()
            );
            paymentTMS.add(paymentTM);
        }
        tnlPayment.setItems(paymentTMS);

    }

    void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        LoadNextID();
        loadTableData();

        btnSve.setDisable(true);
        slipGenBtn.setDisable(false);

        txtPayId.setText("");
        txtPayType.setText("");
        comboPatId.setText("");
        txtConductor.setText("");
        txtCash.setText("");
        txtBalance.setText("");

    }
}
