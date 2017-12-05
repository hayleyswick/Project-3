package GUIControllers;

import Application.SalesVanWarehouse;
import Users.SalesAssociate;
import Users.SysAdmin;
import Users.User;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Handles the gui for the System Admin
 *
 * @author Liam Caudill
 */

public class SysAdminController {

    SysAdmin currentUser = (SysAdmin) Main.userList.get(Main.userIndex);

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

    /**
     * Creates either a default user, or a specific user, based on the selected choices.
     */
    @FXML
    void doCreateButton() {
        boolean exists = false;

        if (createUsername.getText().trim().equals("") || createPassword.getText().trim().equals("")
                || createFirstName.getText().trim().equals("") || createLastName.getText().trim().equals("") ||
                createEmail.getText().trim().equals("") || createPhoneNumber.getText().trim().equals("")) {

            /*
            for (int i = 0; i < Main.userList.size(); i++) {
                createOutput.appendText(Main.userList.get(i).getUsername() + "\n");

            //Use this to see a list of all users and all vans
            }
            for (int i = 0; i < Main.mainDB.getFleet().size(); i++) {
                createOutput.appendText(Main.mainDB.getFleet().get(i).getName() + "\n");
            }
            */

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
                    currentUser.addSysAdmin(newUser);
                    createOutput.appendText("System Admin " + newUser.getUsername() + " created.\n");
                    Main.writer.writeFiles();
                    break;

                case "Office Manager":
                    currentUser.addOfficeManager(newUser);
                    createOutput.appendText("Office Manager " + newUser.getUsername() + " created.\n");
                    Main.writer.writeFiles();
                    break;

                case "Warehouse Manager":
                    currentUser.addWHManager(newUser);
                    createOutput.appendText("Warehouse Manager " + newUser.getUsername() + " created.\n");
                    Main.writer.writeFiles();
                    break;

                case "Sales Associate":
                    if (!createVanName.getText().trim().equals("")) {
                        SalesVanWarehouse temp = new SalesVanWarehouse(createVanName.getText());
                        currentUser.addSalesAssociate(newUser, temp);
                        Main.mainDB.addWarehouse(temp);
                        createOutput.appendText("Sales Associate " + newUser.getUsername() + " created.\n");
                        Main.writer.writeFiles();
                    }
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

    /**
     * Deletes a user from the list based off of the entered username.
     */
    @FXML
    void doDeleteButton() {

        boolean exists = false;
        int index = 0;

        switch (deleteUsername.getText().trim()) {

            case "":
                createOutput.appendText("Please enter the username of the user you wish to delete.\n");
                break;

            case "admin":
                createOutput.appendText("You cannot delete this account.\n");
                break;

            default:
                for (int i = 0; i < Main.userList.size(); i++) {
                    if (Main.userList.get(i).getUsername().equals(deleteUsername.getText().trim())) {
                        exists = true;
                        index = i;

                    }

                }
                if (exists == false) {
                    createOutput.appendText("User does not exist.\n");

                } else {
                    if (Main.userList.get(index).getClass().toString().equals("class Users.SalesAssociate")) {
                        SalesAssociate temp = (SalesAssociate) Main.userList.get(index);
                        for (int i = 0; i < Main.mainDB.getFleet().size(); i++) {
                            if (Main.mainDB.getFleet().get(i).getName().equals(temp.getS().getName())) {
                                Main.mainDB.getFleet().remove(i);
                            }
                        }
                        createOutput.appendText("User " + Main.userList.get(index).getUsername() + " has been deleted.\n");
                        Main.userList.remove(index);
                    } else {
                        createOutput.appendText("User " + Main.userList.get(index).getUsername() + " has been deleted.\n");
                        Main.userList.remove(index);
                    }

                }
                Main.writer.writeFiles();
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

    /**
     * Returns the user to the login screen.
     *
     * @throws IOException Thrown if LoginController.fxml does not exist or is read in incorrectly.
     */

    @FXML
    void doLogoutButton() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.show();
        Main.writer.writeFiles();
        createUserChoice.getScene().getWindow().hide();

    }

}
