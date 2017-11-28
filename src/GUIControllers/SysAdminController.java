package GUIControllers;

import Users.SysAdmin;
import Users.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    void doCreateButton() {
        boolean exists = false;

        if (createUsername.getText().trim().equals("") || createPassword.getText().trim().equals("")
                || createFirstName.getText().trim().equals("") || createLastName.getText().trim().equals("") ||
                createEmail.getText().trim().equals("") || createPhoneNumber.getText().trim().equals("")) {

            for (int i = 0; i < Main.userList.size(); i++) {
                createOutput.appendText(Main.userList.get(i).getUsername() + "\n");

            }
            createOutput.appendText("Please fill out all the required fields.\n");

        } else {

            for (int i = 0; i < Main.userList.size(); i++) {
                if (Main.userList.get(i).getUsername().equals(createUsername.getText())) {
                    exists = true;
                }
            }

            User newUser = new User(createUsername.getText(), createPassword.getText(), createFirstName.getText(),
                    createLastName.getText(), createEmail.getText(), createPhoneNumber.getText());

            switch (createUserChoice.getValue().toString()) {

                case "Default User":
                    if (!exists) {
                        Main.userList.add(newUser);
                        createOutput.appendText("Default User " + newUser.getUsername() + " created.\n");
                    } else {
                        createOutput.appendText("User " + createUsername.getText() + " already exists.\n");
                    }
                    break;

                case "System Admin":

                    break;

            }
        }

    }




    @FXML
    private TableView deleteUserList;

    @FXML
    private TableColumn deleteUserType;

    @FXML
    private TableColumn deleteUsernameColumn;

    @FXML
    private TextField deleteUsername;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea deleteTextArea;

    @FXML
    void doDeleteButton() {

        boolean exists = false;
        int index = 0;

        switch (deleteUsername.getText().trim()) {

            case "":
                deleteTextArea.appendText("Please enter the username of the user you wish to delete.\n");
                break;

            case "admin":
                deleteTextArea.appendText("You cannot delete this account.\n");
                break;

            default:
                for (int i = 0; i < Main.userList.size(); i++) {
                    if (Main.userList.get(i).getUsername().equals(deleteUsername.getText().trim())) {
                        exists = true;
                        index = i;

                    }

                }
                if (exists == false) {
                    deleteTextArea.appendText("User does not exist.\n");

                } else {
                    deleteTextArea.appendText("User " + Main.userList.get(index).getUsername() + " has been deleted.\n");
                    Main.userList.remove(index);

                }


                break;
        }


    }


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
        deleteUsernameColumn.setCellValueFactory(new PropertyValueFactory("Username"));
    }


}
