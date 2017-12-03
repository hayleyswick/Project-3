package GUIControllers;

import Application.Inventory;
import Application.SalesVanWarehouse;
import Application.Warehouse;
import Users.User;
import Users.WHManager;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class WHManagerController {

    WHManager currentUser = (WHManager) Main.userList.get(Main.userIndex);

    @FXML
    private TextField examineTextField;

    @FXML
    private MenuButton examineMenuButton;

    @FXML
    private TextArea examineTextArea;

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
                    examineTextArea.appendText("Location: " + w.getName() + "\nPart Name: " + temp.getBikePart().getName() + ", ID: " + temp.getBikePart().getID() + ", Price: $"
                            + temp.getBikePart().getPrice() + ", Sale Price: $" + temp.getBikePart().getSalePrice() + ", On Sale: "
                            + temp.getBikePart().getIsOnSale() + ", Quantity: " + temp.getQuantity() + "\n");
                    temp = null;
                    exists = true;
                }
            }

            if (!exists) {
                examineTextArea.appendText("No part exists with the name " + examineTextField.getText() + ".\n");
            }
        } else examineTextArea.appendText("Please enter a BikePart name or ID.\n");
    }

    @FXML
    void doExamineID() {

        Inventory temp;
        ArrayList<SalesVanWarehouse> inventory = Main.mainDB.getAllInventories();
        boolean exists = false;

        if (!examineTextField.getText().trim().equals("")) {

            temp = currentUser.examinePartName(examineTextField.getText(), Main.mainDB.getFleet().get(0).getDB());

            for (SalesVanWarehouse w : inventory) {

                temp = currentUser.examinePartID(Long.parseLong(examineTextField.getText()), w.getDB());

                if (temp != null) {
                    examineTextArea.appendText("Location: " + w.getName() + "\nPart Name: " + temp.getBikePart().getName() + ", ID: " + temp.getBikePart().getID() + ", Price: $"
                            + temp.getBikePart().getPrice() + ", Sale Price: $" + temp.getBikePart().getSalePrice() + ", On Sale: "
                            + temp.getBikePart().getIsOnSale() + ", Quantity: " + temp.getQuantity() + "\n");
                    temp = null;
                    exists = true;
                }
            }

            if (!exists) {
                examineTextArea.appendText("No part exists with the name " + examineTextField.getText() + ".\n");
            }
        } else examineTextArea.appendText("Please enter a BikePart name or ID.\n");
    }

    @FXML
    public TextArea updateInventoryArea;

    @FXML
    public TextField updateInventoryName;

    @FXML
    void updateInventory() {

        ArrayList<Inventory> incomingInventory;

        if (!updateInventoryName.getText().trim().equals("")) {

            File tempFile = new File(("src/Files/" + updateInventoryName.getText() + ".ser"));

            if (tempFile.isFile()) {
                incomingInventory = (ArrayList<Inventory>) Main.writer.readFromFile(updateInventoryName.getText());
                for(Inventory i: incomingInventory){
                    updateInventoryArea.appendText("Part Name: " + i.getBikePart().getName() + ", ID: " + i.getBikePart().getID() + ", Price: $"
                            + i.getBikePart().getPrice() + ", Sale Price: $" + i.getBikePart().getSalePrice() + ", On Sale: "
                            + i.getBikePart().getIsOnSale() + ", Quantity: " + i.getQuantity() + "\n");

                }

            } else {
                updateInventoryArea.appendText("File "+updateInventoryName.getText()+" does not exist.\n");
            }

        }


    }
}

