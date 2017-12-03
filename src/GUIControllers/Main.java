package GUIControllers;

import Users.*;
import Application.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<User> userList = new ArrayList<>();

    public static MainWareHouse mainDB = new MainWareHouse();

    public static int userIndex = -1;

    public static Stage userStage = new Stage();

    public static Stage loginStage = new Stage();

    public static FileHandler writer = new FileHandler();

    public void start(Stage loginStage) throws Exception {

        ObjectInputStream objectinputstream = null;
        boolean test = false;

        if (!test) {
            try {
                FileInputStream streamIn = new FileInputStream("src/Files/DBinventory.ser");
                objectinputstream = new ObjectInputStream(streamIn);
                ArrayList<Inventory> mainInv = (ArrayList<Inventory>) objectinputstream.readObject();
                mainDB.setDB(mainInv);
                streamIn = new FileInputStream("src/Files/fleet.ser");
                objectinputstream = new ObjectInputStream(streamIn);
                ArrayList<SalesVanWarehouse> fleet = (ArrayList<SalesVanWarehouse>) objectinputstream.readObject();
                mainDB.setFleet(fleet);
                streamIn = new FileInputStream("src/Files/users.ser");
                objectinputstream = new ObjectInputStream(streamIn);
                userList = (ArrayList<User>) objectinputstream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (objectinputstream != null) {
                    objectinputstream.close();
                }
            }
        }


        if (test) {
            SalesVanWarehouse van1 = new SalesVanWarehouse("Tim's Van");

            SysAdmin defaultAdmin = new SysAdmin("admin", "minda",
                    "admin@gmail.com", "John", "Roberts", "7035551375");
            OfficeManager testOfficeManager = new OfficeManager("bross", "test1",
                    "b.ross@gmail.com", "Betsy", "Ross", "5911522255");
            WHManager testWHManager = new WHManager("toast", "test2",
                    "j.norton@gmail.com", "James", "Norton", "7032210905");
            SalesAssociate testSalesAssociate = new SalesAssociate(new User("sassociate", "test3",
                    "test@gmail.com", "Tim", "Simpson", "7037328121"), van1);
            User testUser = new User("user", "test4",
                    "test@gmail.com", "joe", "schmoe", "7037328121");


            userList.add(defaultAdmin);
            userList.add(testOfficeManager);
            userList.add(testWHManager);
            userList.add(testSalesAssociate);
            userList.add(testUser);

            Inventory testInv1 = new Inventory(new BikePart("test1", 123, 10, 5, false), 5);
            Inventory testInv2 = new Inventory(new BikePart("test2", 456, 10, 5, false), 5);
            Inventory testInv3 = new Inventory(new BikePart("test3", 789, 10, 5, true), 5);
            Inventory testInv4 = new Inventory(new BikePart("test4", 000, 10, 5, false), 5);

            mainDB.addInventory(testInv1);
            mainDB.addInventory(testInv2);
            mainDB.addInventory(testInv3);
            van1.addInventory(testInv4);
            van1.addInventory(testInv1);
            mainDB.addWarehouse(van1);

        }


        System.out.println("Name: " + mainDB.getDB().get(0).getBikePart().getName() + ", ID: " + mainDB.getDB().get(0).getBikePart().getID() + ", Price: $"
                + mainDB.getDB().get(0).getBikePart().getPrice() + ", Sale Price: $" + mainDB.getDB().get(0).getBikePart().getSalePrice() + ", On Sale: "
                + mainDB.getDB().get(0).getBikePart().getIsOnSale() + ", Quantity: " + mainDB.getDB().get(0).getQuantity() + ".\n");


        Parent root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        loginStage.setTitle("User Login");
        loginStage.setScene(new Scene(root, 325, 275));
        loginStage.show();

        loginStage.setOnCloseRequest(we -> {
            writer.writeFiles();
        });


    }


    public static void main(String[] args) {
        launch(args);

    }


}
