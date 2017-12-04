package GUIControllers;

import java.io.FileNotFoundException;

import Application.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import Users.*;

public class SalesAssociateController {
    private SalesAssociate currentUser = (SalesAssociate) Main.userList.get(Main.userIndex);
    public TextField textField;
    public TextArea textArea;
    public ObservableList<String> choices = FXCollections.observableArrayList("Sell to bike shop", "Load van");
    public ComboBox<String> choice;
    public Button button;

    public void setBox() {
        choice.setItems(choices);
    }

    public void setButton() {
        if (choice.getValue().equals("Sell to bike shop"))
            button.setText("Sell");
        else
            button.setText("Load Van");
    }

    public void initialize() {
        choice.setValue("Sell to bike shop");

    }


    public void Go() {
        switch (choice.getValue()) {
            case "Sell to bike shop":
                try {
                    for (Inventory i : currentUser.getS().getDB()) {
                        textArea.appendText("\nPart Name: " + i.getBikePart().getName() + ", ID: " + i.getBikePart().getID() + ", Price: $"
                                + i.getBikePart().getPrice() + ", Sale Price: $" + i.getBikePart().getSalePrice() + ", On Sale: "
                                + i.getBikePart().getIsOnSale() + ", Quantity: " + i.getQuantity() + "\n");

                    }
                    textArea.appendText(currentUser.sellToBikeShop(textField.getText()));
                    for (Inventory i : currentUser.getS().getDB()) {
                        textArea.appendText("\nPart Name: " + i.getBikePart().getName() + ", ID: " + i.getBikePart().getID() + ", Price: $"
                                + i.getBikePart().getPrice() + ", Sale Price: $" + i.getBikePart().getSalePrice() + ", On Sale: "
                                + i.getBikePart().getIsOnSale() + ", Quantity: " + i.getQuantity() + "\n");

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "Load van":

                //System.out.println(Main.mainDB.getDB().get(0).getQuantity());
                //textArea.setText(currentUser.loadVan(textField.getText(), Main.mainDB));
                currentUser.moveParts(textField.getText());
                Main.writer.writeFiles();

                for (Inventory i : currentUser.getS().getDB()) {
                    textArea.appendText("Part Name: " + i.getBikePart().getName() + ", ID: " + i.getBikePart().getID() + ", Price: $"
                            + i.getBikePart().getPrice() + ", Sale Price: $" + i.getBikePart().getSalePrice() + ", On Sale: "
                            + i.getBikePart().getIsOnSale() + ", Quantity: " + i.getQuantity() + "\n");

                }
                for (Inventory i : Main.mainDB.getDB()) {
                    textArea.appendText("Part Name: " + i.getBikePart().getName() + ", ID: " + i.getBikePart().getID() + ", Price: $"
                            + i.getBikePart().getPrice() + ", Sale Price: $" + i.getBikePart().getSalePrice() + ", On Sale: "
                            + i.getBikePart().getIsOnSale() + ", Quantity: " + i.getQuantity() + "\n");

                }


        }
    }
}