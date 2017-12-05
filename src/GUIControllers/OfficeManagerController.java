package GUIControllers;

import Application.Inventory;
import Application.SalesVanWarehouse;
import Users.OfficeManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Handles the gui for the OfficeManager
 *
 * @author Liam Caudill
 */
public class OfficeManagerController {

    OfficeManager currentUser = (OfficeManager) Main.userList.get(Main.userIndex);

    @FXML
    private TextField examineTextField;

    @FXML
    private MenuButton examineMenuButton;

    @FXML
    private TextArea examineTextArea;

    /**
     * Searches for Inventory by name; if there is a match, displays all the attributes of the part
     * and where it can be found in the system.
     */

    @FXML
    void doExamineName() {

        Inventory temp;
        ArrayList<SalesVanWarehouse> inventory = Main.mainDB.getAllInventories();
        boolean exists = false;

        if (!examineTextField.getText().trim().equals("")) {


            temp = currentUser.examinePartName(examineTextField.getText(), Main.mainDB.getFleet().get(0).getDB());

            for (SalesVanWarehouse w : inventory) {

                temp = currentUser.examinePartName(examineTextField.getText(), w.getDB());

                if (temp != null) {
                    examineTextArea.appendText("\nLocation: " + w.getName() + "\n" + temp.appendTextFormat());
                    temp = null;
                    exists = true;
                }

            }

            if (!exists) {
                examineTextArea.appendText("No part exists with the name " + examineTextField.getText() + ".\n");
            }
        } else examineTextArea.appendText("Please enter a BikePart name or ID.\n");
    }

    /**
     * Searches for Inventory by ID; if there is a match, displays all the attributes of the part
     * and where it can be found in the system.
     */
    @FXML
    void doExamineID() throws NumberFormatException {

        Inventory temp = null;
        ArrayList<SalesVanWarehouse> inventory = Main.mainDB.getAllInventories();
        boolean exists = false;

        if (!examineTextField.getText().trim().equals("")) {

            examineTextArea.appendText("\n");

            try {
                temp = currentUser.examinePartID(Long.parseLong(examineTextField.getText()), Main.mainDB.getFleet().get(0).getDB());

            } catch (Exception e) {
            }

            for (SalesVanWarehouse w : inventory) {

                try {
                    temp = currentUser.examinePartID(Long.parseLong(examineTextField.getText()), w.getDB());
                } catch (Exception e) {
                }

                if (temp != null) {
                    examineTextArea.appendText("\nLocation: " + w.getName() + "\n" + temp.appendTextFormat());
                    temp = null;
                    exists = true;
                }

            }

            if (!exists) {
                examineTextArea.appendText("No part exists with the ID " + examineTextField.getText() + ".\n");
            }
        } else examineTextArea.appendText("Please enter a BikePart name or ID.\n");
    }

    /**
     * Sorts BikeParts in every location by name, but keeps the inventories separate.
     */
    @FXML
    void doSortName() {
        ArrayList<SalesVanWarehouse> inventory = Main.mainDB.getAllInventories();

        for (SalesVanWarehouse s : inventory) {
            examineTextArea.appendText("\nLocation: " + s.getName() + "\n");
            Collections.sort(s.getDB(), (p1, p2) -> p1.getBikePart().getName().compareToIgnoreCase(p2.getBikePart().getName()));
            for (Inventory i : s.getDB()) {
                examineTextArea.appendText(i.appendTextFormat());
            }
        }
    }

    /**
     * Sorts BikeParts in every location by ID, but keeps the inventories separate.
     */
    @FXML
    void doSortID() {
        ArrayList<SalesVanWarehouse> inventory = Main.mainDB.getAllInventories();

        for (SalesVanWarehouse s : inventory) {
            examineTextArea.appendText("\nLocation: " + s.getName() + "\n");
            Collections.sort(s.getDB(), (p1, p2) -> Long.compare(p1.getBikePart().getID(), p2.getBikePart().getID()));

            for (Inventory i : s.getDB()) {
                examineTextArea.appendText(i.appendTextFormat());
            }
        }
    }

    @FXML
    public TextField orderQuantityField;

    @FXML
    public TextArea orderTextArea;

    @FXML
    public TextField orderNameField;

    /**
     * Creates a serializable inventory file with the given name and with the quantities for each part
     * set to a number such that, once read in, every part in the list will be at the quantity specified.
     */
    @FXML
    void doOrderButton() {

        if (!orderQuantityField.getText().trim().equals("") && !orderNameField.getText().trim().equals("")) {

            File tempFile = new File(("src/Files/" + orderNameField.getText() + ".ser"));
            if (!tempFile.isFile()) {

                ArrayList<Inventory> parts = currentUser.generatePartsOrder(Long.parseLong(orderQuantityField.getText()));
                orderTextArea.appendText("Parts to be ordered:\n");

                for (Inventory i : parts) {
                    orderTextArea.appendText(i.appendTextFormat());

                }

                Main.writer.writeToFile(orderNameField.getText(), parts);
                orderTextArea.appendText("Inventory file " + orderNameField.getText() + ".ser created.\n");
            } else {
                orderTextArea.appendText("A file with the name " + orderNameField.getText() + " already exists.\n");

            }
        } else {
            orderTextArea.appendText("Please fill out every field.\n");
        }

    }

    @FXML
    public DatePicker startDate;

    @FXML
    public DatePicker endDate;

    @FXML
    public TextArea commissionTextArea;

    @FXML
    public TextField usernameTextField;


    /**
     * Generates the commission of an entered Sales Associate based off of the start and end
     * date selected by summing the sale prices from invoices and deriving 15%
     */
    @FXML
    void doFindDate() {

        int year = startDate.getValue().getYear();
        int month = startDate.getValue().getMonthValue() - 1;
        int day = startDate.getValue().getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        Date start = calendar.getTime();

        year = endDate.getValue().getYear();
        month = endDate.getValue().getMonthValue() - 1;
        day = endDate.getValue().getDayOfMonth();

        calendar.set(year, month, day);

        Date end = calendar.getTime();

        if (!start.after(end)) {

            commissionTextArea.appendText(currentUser.paySalesAssociate(usernameTextField.getText(), start, end) + " from " + start.toString() + " to "
                    + end.toString() + ".\n");


        }


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
        commissionTextArea.getScene().getWindow().hide();

    }

}
