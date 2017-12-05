package GUIControllers;

import Application.Inventory;
import Application.SalesInvoice;
import Application.SalesVanWarehouse;
import Users.SalesAssociate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Handles all aspects of the SalesAssociate GUI
 *
 * @author Liam Caudill & Will Jones
 */

public class SalesAssociateController {


    private SalesAssociate currentUser = (SalesAssociate) Main.userList.get(Main.userIndex);
    @FXML
    public TextField textField;
    @FXML
    public TextArea textArea;
    @FXML
    public ObservableList<String> choices = FXCollections.observableArrayList("Sell to Bike Shop", "Load Van");
    @FXML
    public ComboBox<String> choice;
    @FXML
    public Button button;


    @FXML
    public void setBox() {
        choice.setItems(choices);
    }

    @FXML
    public void setButton() {
        if (choice.getValue().equals("Sell to Bike Shop"))
            button.setText("Sell");
        else
            button.setText("Load Van");
    }

    @FXML
    public void initialize() {
        choice.setValue("Sell to Bike Shop");

    }

    /**
     * Loads the sales van of the logged in sales associate or sells parts to a bike shop, depending on the selected option.
     * Both functions require reading in a file, a .txt file for selling and a .ser serializable file for the loading.
     */
    @FXML
    public void Go() {
        switch (choice.getValue()) {
            case "Sell to Bike Shop":
                try {

                    textArea.appendText(currentUser.sellToBikeShop(textField.getText()));

                    for (int i = 0; i < Main.mainDB.getFleet().size(); i++) {
                        if (Main.mainDB.getFleet().get(i).getName().equals(currentUser.getS().getName())) {
                            Main.mainDB.getFleet().set(i, currentUser.getS());
                        }
                    }
                    Main.writer.writeFiles();


                } catch (FileNotFoundException e) {
                    textArea.appendText("File " + textField.getText() + ".txt does not exist.\n");

                }
                break;
            case "Load Van":

                File inv = new File("src/Files/" + textField.getText() + ".ser");
                if (inv.isFile()) {
                    ArrayList<Inventory> temp = (ArrayList<Inventory>) Main.writer.readFromFile(textField.getText());

                    currentUser.moveParts(textField.getText());

                    textArea.appendText("Parts added to Sales Van:\n");
                    for (Inventory i : temp) {
                        textArea.appendText(i.appendTextFormat());

                    }
                    for (int i = 0; i < Main.mainDB.getFleet().size(); i++) {
                        if (Main.mainDB.getFleet().get(i).getName().equals(currentUser.getS().getName())) {
                            Main.mainDB.getFleet().set(i, currentUser.getS());
                        }
                    }
                    Main.writer.writeFiles();

                } else {
                    textArea.appendText("File " + textField.getText() + ".ser does not exist.\n");
                }

        }
    }

    /**
     * Hidden testing button, used to get main warehouse inventory.
     */

    @FXML
    void doTest() {
        textArea.appendText("\nMain Database\n");
        for (Inventory j : Main.mainDB.getDB()) {
            textArea.appendText(j.appendTextFormat());
        }
        textArea.appendText("\nMain Database Warehouse Inventory\n");

        for (SalesVanWarehouse s : Main.mainDB.getFleet()) {

            for (Inventory i : s.getDB()) {
                textArea.appendText(i.appendTextFormat());

            }
            textArea.appendText("");
        }
        textArea.appendText("\nSales Associate Inventory\n");

        for (Inventory i : currentUser.getS().getDB()) {
            textArea.appendText(i.appendTextFormat());

        }
        textArea.appendText("");

    }

    /**
     * Views the entire inventory of the SalesVan
     */

    @FXML
    void viewInventory() {
        textArea.appendText("\n" + currentUser.getFirstName() + "'s Van Inventory\n");

        for (Inventory i : currentUser.getS().getDB()) {
            textArea.appendText(i.appendTextFormat());

        }
        textArea.appendText("");

    }

    @FXML
    public DatePicker startDate;

    @FXML
    public DatePicker endDate;

    @FXML
    public TextArea invoiceTextArea;

    @FXML
    public TextField usernameTextField;


    /**
     * Views the Sales Invoices of the logged in associate starting at the start date and ending at the end date.
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
            for (SalesInvoice i : currentUser.getInvoices()) {
                if (i.getDate().after(start) && i.getDate().before(end)) {
                    invoiceTextArea.appendText(i.getInvoice());
                }

            }


        } else {
            invoiceTextArea.appendText("Incorrect date selections.\n");

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
        invoiceTextArea.getScene().getWindow().hide();

    }
}