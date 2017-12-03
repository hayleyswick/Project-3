package GUIControllers;

import Application.Inventory;
import Application.SalesVanWarehouse;
import Application.Warehouse;
import Users.OfficeManager;
import Users.WHManager;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;


public class OfficeManagerController {

    OfficeManager currentUser = (OfficeManager) Main.userList.get(Main.userIndex);

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
    public TextField orderQuantityField;

    @FXML
    public TextArea orderTextArea;

    @FXML
    public TextField orderNameField;

    @FXML
    void doOrderButton() {

        if (!orderQuantityField.getText().trim().equals("") && !orderNameField.getText().trim().equals("")) {

            File tempFile = new File(("src/Files/"+orderNameField.getText()+".ser"));
            if (!tempFile.isFile()) {

                ArrayList<Inventory> parts = currentUser.generatePartsOrder(Long.parseLong(orderQuantityField.getText()));
                orderTextArea.appendText("Parts to be ordered:\n");

                for (Inventory i : parts) {
                    orderTextArea.appendText("Part Name: " + i.getBikePart().getName() + ", ID: " + i.getBikePart().getID() + ", Price: $"
                            + i.getBikePart().getPrice() + ", Sale Price: $" + i.getBikePart().getSalePrice() + ", On Sale: "
                            + i.getBikePart().getIsOnSale() + ", Quantity: " + i.getQuantity() + "\n");

                }

                Main.writer.writeToFile(orderNameField.getText(), parts);
                orderTextArea.appendText("Inventory file "+orderNameField.getText()+".ser created.\n");
            } else{
                orderTextArea.appendText("A file with the name "+orderNameField.getText()+" already exists.\n");

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
    void doFindDate() {

        System.out.println(startDate.getValue() + " " + endDate.getValue());


    }

}
