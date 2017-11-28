package GUIControllers;

import Application.Inventory;
import Application.Warehouse;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WHManagerController {

    @FXML
    private TextField examineTextField;

    @FXML
    private MenuButton examineMenuButton;

    @FXML
    private TextArea examineTextArea;

    @FXML
    void doExamineName() {

        boolean exists = false;

        if (!examineTextField.getText().trim().equals("")) {

            for (Warehouse w : Main.mainDB.getTotalInventory()) {
                for (Inventory b : w.getDB()) {
                    if (examineTextField.getText().equals(b.getBikePart().getName())) {
                        examineTextArea.appendText("Name: " + b.getBikePart().getName() + ", ID: " + b.getBikePart().getID() + ", Price: $"
                                + b.getBikePart().getPrice() + ", Sale Price: $" + b.getBikePart().getSalePrice() + ", On Sale: "
                                + b.getBikePart().getIsOnSale() + ", Quantity: " + b.getQuantity() + "\n");
                        exists = true;
                    }
                }

            }
            if (!exists) {
                examineTextArea.appendText("No part exists with the name " + examineTextField.getText() + ".\n");
            }
        } else {
            examineTextArea.appendText("Please enter a BikePart name or ID.\n");
        }
    }

    @FXML
    void doExamineID() {

        boolean exists = false;

        if (!examineTextField.getText().trim().equals("")) {

            for (Warehouse w : Main.mainDB.getTotalInventory()) {
                for (Inventory b : w.getDB()) {
                    if (examineTextField.getText().equals(Long.toString(b.getBikePart().getID()))) {
                        examineTextArea.appendText("Name: " + b.getBikePart().getName() + ", ID: " + b.getBikePart().getID() + ", Price: $"
                                + b.getBikePart().getPrice() + ", Sale Price: $" + b.getBikePart().getSalePrice() + ", On Sale: "
                                + b.getBikePart().getIsOnSale() + ", Quantity: " + b.getQuantity() + "\n");
                        exists = true;
                    }
                }

            }
            if (!exists) {
                examineTextArea.appendText("No part exists with the ID " + examineTextField.getText() + ".\n");
            }

        } else {
            examineTextArea.appendText("Please enter a BikePart name or ID.\n");
        }
    }
}
