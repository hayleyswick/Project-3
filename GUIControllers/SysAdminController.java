package GUIControllers;

import Users.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class SysAdminController {

    @FXML
    private TextField createUsername;

    @FXML
    private TextField createPassword;

    @FXML
    private TextField createFirstName;

    @FXML
    private TextField createLastName;

    @FXML
    private TextField createEmail;

    @FXML
    private TextField createPhoneNumber;

    @FXML
    private ChoiceBox createUserChoice;

    ObservableList<String> userType = FXCollections.observableArrayList(
            "Default User", "System Admin", "Office Manager", "Warehouse Manager", "Sales Associate");
    @FXML
    private TextField createVanName;

    @FXML
    private TextArea createOutput;

    @FXML
    private Button createButton;

    @FXML
    void doCreateButton(){

        if(createUsername.getText().trim().equals("")||createPassword.getText().trim().equals("")
                ||createFirstName.getText().trim().equals("")||createLastName.getText().trim().equals("")||
                createEmail.getText().trim().equals("")||createPhoneNumber.getText().trim().equals("")){
            createOutput.appendText("Please fill out all the required fields.\n");

        } else{

        }

    }

    @FXML
    private TableView deleteUserList;

    @FXML
    private TableColumn deleteUserType;

    @FXML
    private TableColumn deleteUsername;


    @FXML
    private void initialize() throws IOException {
        createUserChoice.setValue("Default User");
        createUserChoice.setItems(userType);
        createVanName.setVisible(false);
        createVanName.setEditable(false);
        createUserChoice.valueProperty().addListener((ChangeListener<String>) (ov, t, t1) -> {
            if (t1.equals("Sales Associate")) {
                createVanName.setVisible(true);
                createVanName.setEditable(true);

            } else {
                createVanName.setVisible(false);
                createVanName.setEditable(false);

            }

        });
        deleteUserType.setCellValueFactory(new PropertyValueFactory("User Type"));
        deleteUsername.setCellValueFactory(new PropertyValueFactory("Username"));
    }


}
