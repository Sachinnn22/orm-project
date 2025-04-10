package ormsuperorm.ormsuper.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ormsuperorm.ormsuper.bo.BOFactory;
import ormsuperorm.ormsuper.bo.custom.UserBO;
import ormsuperorm.ormsuper.dto.TherapistDTO;
import ormsuperorm.ormsuper.dto.UserDTO;
import ormsuperorm.ormsuper.tm.TherapyTM;
import ormsuperorm.ormsuper.tm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtRole;

    @FXML
    private TableColumn<?, ?> userIdCol;

    @FXML
    private TextField userIdTxt;

    @FXML
    private TableColumn<?, ?> userNameCol;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TableColumn<?, ?> userPswrdCol;

    @FXML
    private TextField userPswrdTxt;

    @FXML
    private TableColumn<?, ?> userRoleCol;

    @FXML
    private TableView<?> userTable;

    @FXML
    void btnDeleteAcc(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String ID = userIdTxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDelete = userBO.delete(ID);
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
    void btnSaveAcc(ActionEvent event) {
        String userId = userIdTxt.getText();
        String userName = userNameTxt.getText();
        String userPassword = userPswrdTxt.getText();
        String role = txtRole.getText();

        try {
            boolean isSaved = userBO.save(new UserDTO(userId,userName,userPassword,role));
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
    void btnUpdateAcc(ActionEvent event) {
        String userId = userIdTxt.getText();
        String userName = userNameTxt.getText();
        String userPassword = userPswrdTxt.getText();
        String role = txtRole.getText();

        try {
            boolean isSaved = userBO.update(new UserDTO(userId,userName,userPassword,role));
            if(isSaved){
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"User update SUCCESSFULLY 😎").show();

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
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userPswrdCol.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        userRoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadNextID() throws SQLException, IOException {
        String nextID = userBO.getNextId();
        userIdTxt.setText(nextID);
    }
    private void loadTableData() throws SQLException, ClassNotFoundException, IOException {
        ArrayList<UserDTO> userDTOS = (ArrayList<UserDTO>) userBO.getAll();
        ObservableList<UserTM> userTMS = FXCollections.observableArrayList();

        for (UserDTO userDTO : userDTOS) {
            UserTM userTM = new UserTM(
                    userDTO.getUserId(),
                    userDTO.getUserName(),
                    userDTO.getUserPassword(),
                    userDTO.getRole()

            );
            userTMS.add(userTM);
        }
        userTable.setItems(userTMS);
    }

    void refreshPage() throws SQLException, ClassNotFoundException, IOException {
        LoadNextID();
        loadTableData();

        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(true);

        userIdTxt.setText("");
        userNameTxt.setText("");
        userPswrdTxt.setText("");
        txtRole.setText("");

    }
}

