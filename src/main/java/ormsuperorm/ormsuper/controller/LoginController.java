package ormsuperorm.ormsuper.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ormsuperorm.ormsuper.bo.BOFactory;
import ormsuperorm.ormsuper.bo.custom.UserBO;
import org.mindrot.jbcrypt.BCrypt;
import ormsuperorm.ormsuper.dto.UserDTO;

public class LoginController {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER)

    @FXML
    private ImageView eyeIcon;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane loginanc1;

    @FXML
    private TextField pswrdTTx;

    @FXML
    private PasswordField pswrdTxt;

    @FXML
    private TextField usrTxt;

    @FXML
    void eyeHoverEnter(MouseEvent event) {

    }

    @FXML
    void eyeHoverExit(MouseEvent event) {

    }

    @FXML
    void loginBtnOnAcc(ActionEvent event) {
        String userName = usrTxt.getText();
        String password = pswrdTxt.getText();

        String dbPassword =  getUserPassword();

        boolean isPasswordCorrect = BCrypt.checkpw(password,dbPassword);
        if (isPasswordCorrect) {

            if (userName.equals("Admin")) {
                AdminDashboard();
            } else {
                ResipDashboard();
            }

        } else {
            new Alert(Alert.AlertType.ERROR,"Invalid Password.Try Again").show();
        }
    }

    public String getUserPassword() {
        String name = usrTxt.getText();
        try {
            UserDTO user = userBO.getUser(name);
            return user.getUserPassword();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
