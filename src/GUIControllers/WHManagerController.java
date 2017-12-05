package GUIControllers;

import Application.Inventory;
import Application.SalesVanWarehouse;
import Users.WHManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Handles the gui for the Warehouse Manager
 */
public class WHManagerController {

    WHManager currentUser = (WHManager) Main.userList.get(Main.userIndex);

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

    @FXML
    public TextArea updateInventoryArea;

    @FXML
    public TextField updateInventoryName;

    /**
     * Reads in a serializable file created by the Office Manager and updates the stock.
     */
    @FXML
    void updateInventory() {

        ArrayList<Inventory> incomingInventory;
        long[] idxAndQuantity = new long[2];
        long quantity;

        if (!updateInventoryName.getText().trim().equals("")) {

            File tempFile = new File(("src/Files/" + updateInventoryName.getText() + ".ser"));

            if (tempFile.isFile()) {
                incomingInventory = (ArrayList<Inventory>) Main.writer.readFromFile(updateInventoryName.getText());
                for (Inventory i : incomingInventory) {

                    idxAndQuantity = Main.mainDB.checkPartList(i, Main.mainDB.getDB());

                    if (idxAndQuantity == null) {
                        Main.mainDB.addInventory(i);
                    } else {
                        quantity = (i.getQuantity() + idxAndQuantity[1]);
                        Main.mainDB.updateInventory(i, quantity, true, Main.mainDB.getDB());
                    }
                }
                Main.writer.writeFiles();

                updateInventoryArea.appendText("Inventory from file " + updateInventoryName.getText() + " has been loaded.");

            } else {
                updateInventoryArea.appendText("File " + updateInventoryName.getText() + " does not exist.\n");
            }
        }
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
        examineTextArea.appendText("\n");
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
        examineTextArea.getScene().getWindow().hide();

    }
}

