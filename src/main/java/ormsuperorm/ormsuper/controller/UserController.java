package ormsuperorm.ormsuper.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

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
    private TableView<?> userTable;

    @FXML
    void btnDeleteAcc(ActionEvent event) {

    }

    @FXML
    void btnSaveAcc(ActionEvent event) {

    }

    @FXML
    void btnUpdateAcc(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
