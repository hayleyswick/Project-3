package GUIControllers;

import Application.Inventory;
import Application.Warehouse;
import Users.WHManager;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

        if (!examineTextField.getText().trim().equals("")) {

            temp = currentUser.examinePartName(examineTextField.getText(), Main.mainDB.getTotalInventory().get(0).getDB());

            if (temp == null) {
                examineTextArea.appendText("No part exists with the name " + examineTextField.getText() + ".\n");
            } else {
                examineTextArea.appendText("Name: " + temp.getBikePart().getName() + ", ID: " + temp.getBikePart().getID() + ", Price: $"
                        + temp.getBikePart().getPrice() + ", Sale Price: $" + temp.getBikePart().getSalePrice() + ", On Sale: "
                        + temp.getBikePart().getIsOnSale() + ", Quantity: " + temp.getQuantity() + "\n");
            }
        } else examineTextArea.appendText("Please enter a BikePart name or ID.\n");
    }

    @FXML
    void doExamineID() {
        Inventory temp;

        if (!examineTextField.getText().trim().equals("")) {

            temp = currentUser.examinePartID(Long.parseLong(examineTextField.getText()), Main.mainDB.getTotalInventory().get(0).getDB());

            if (temp == null) {
                examineTextArea.appendText("No part exists with the name " + examineTextField.getText() + ".\n");
            } else {
                examineTextArea.appendText("Name: " + temp.getBikePart().getName() + ", ID: " + temp.getBikePart().getID() + ", Price: $"
                        + temp.getBikePart().getPrice() + ", Sale Price: $" + temp.getBikePart().getSalePrice() + ", On Sale: "
                        + temp.getBikePart().getIsOnSale() + ", Quantity: " + temp.getQuantity() + "\n");
            }
        } else {
            examineTextArea.appendText("Please enter a BikePart name or ID.\n");
        }

    }
}

