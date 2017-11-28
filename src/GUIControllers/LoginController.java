package GUIControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    //Login Window Elements
    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private TextArea loginOutput;

    @FXML
    void doLoginButton() throws IOException {

        Main.userIndex = -1;

        Stage loadStage = (Stage) loginButton.getScene().getWindow();

        for (int i = 0; i < Main.userList.size(); i++) {
            if (Main.userList.get(i).getUsername().equals(loginUsername.getText()) &&
                    Main.userList.get(i).getPassword().equals(loginPassword.getText())) {
                Main.userIndex = i;
            }

        }

        if (Main.userIndex != -1) {
            switch (Main.userList.get(Main.userIndex).getClass().getName()) {

                case "Users.SysAdmin":
                    Parent adminWindow = FXMLLoader.load(getClass().getResource("SysAdminController.fxml"));
                    Scene adminScene = new Scene(adminWindow);
                    Main.userStage.setScene(adminScene);
                    Main.userStage.show();
                    loadStage.hide();
                    break;

                case "Users.OfficeManager":
                    Parent officeManagerWindow = FXMLLoader.load(getClass().getResource("OfficeManagerController.fxml"));
                    Scene officeManagerScene = new Scene(officeManagerWindow);
                    Main.userStage.setScene(officeManagerScene);
                    Main.userStage.show();
                    loadStage.hide();
                    break;

                case "Users.WHManager":
                    Parent WHManagerWindow = FXMLLoader.load(getClass().getResource("WHManagerController.fxml"));
                    Scene WHManagerScene = new Scene(WHManagerWindow);
                    Main.userStage.setScene(WHManagerScene);
                    Main.userStage.show();
                    loadStage.hide();
                    break;

                case "Users.SalesAssociate":
                    Parent salesAssociateWindow = FXMLLoader.load(getClass().getResource("SalesAssociateController.fxml"));
                    Scene salesAssociateScene = new Scene(salesAssociateWindow);
                    Main.userStage.setScene(salesAssociateScene);
                    Main.userStage.show();
                    loadStage.hide();
                    break;

                case "Users.User":
                    loginOutput.appendText(Main.userList.get(Main.userIndex).getUsername() + " has not been assigned a role.\n");

            }


        } else {
            loginOutput.appendText("Incorrect Username/Password\n");

        }
    }


}



