package GUIControllers;

import Users.*;
import Application.BikePart;
import Application.MainWareHouse;
import Application.Warehouse;
import Application.Inventory;
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

    public void start(Stage loginStage) throws Exception {

        ObjectInputStream objectinputstream = null;

        try {
            FileInputStream streamIn = new FileInputStream("src/GUIControllers/DBinventory.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<Inventory> mainInv = (ArrayList<Inventory>) objectinputstream.readObject();
            mainDB.setDB(mainInv);
            streamIn = new FileInputStream("src/GUIControllers/fleet.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<Warehouse> fleet = (ArrayList<Warehouse>) objectinputstream.readObject();
            mainDB.setFleet(fleet);
            streamIn = new FileInputStream("src/GUIControllers/users.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            userList = (ArrayList<User>) objectinputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                objectinputstream.close();
            }
        }
        /*

        SysAdmin defaultAdmin = new SysAdmin("admin", "minda",
                "test@gmail.com", "joe", "schmoe", "7037328121");
        OfficeManager testOfficeManager = new OfficeManager("omanager", "test1",
                "test@gmail.com", "joe", "schmoe", "7037328121");
        WHManager testWHManager = new WHManager("whmanager", "test2",
                "test@gmail.com", "joe", "schmoe", "7037328121");
        SalesAssociate testSalesAssociate = new SalesAssociate("sassociate", "test3",
                "test@gmail.com", "joe", "schmoe", "7037328121");
        User testUser = new User("user", "test4",
                "test@gmail.com", "joe", "schmoe", "7037328121");


        userList.add(defaultAdmin);
        userList.add(testOfficeManager);
        userList.add(testWHManager);
        userList.add(testSalesAssociate);
        userList.add(testUser);

        /*


        Inventory testInv1 = new Inventory(new BikePart("test1",123,10,5,false),5);
        Inventory testInv2 = new Inventory(new BikePart("test2",456,10,5,false),5);
        Inventory testInv3 = new Inventory(new BikePart("test3",789,10,5,true),5);






        mainDB.addInventory(testInv1);
        mainDB.addInventory(testInv2);
        mainDB.addInventory(testInv3);
        */

        System.out.println("Name: " + mainDB.getDB().get(0).getBikePart().getName() + ", ID: " + mainDB.getDB().get(0).getBikePart().getID() + ", Price: $"
                + mainDB.getDB().get(0).getBikePart().getPrice() + ", Sale Price: $" + mainDB.getDB().get(0).getBikePart().getSalePrice() + ", On Sale: "
                + mainDB.getDB().get(0).getBikePart().getIsOnSale() + ", Quantity: " + mainDB.getDB().get(0).getQuantity() + ".\n");


        Parent root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        loginStage.setTitle("User Login");
        loginStage.setScene(new Scene(root, 325, 275));
        loginStage.show();

        loginStage.setOnCloseRequest(we -> {

            FileOutputStream fout = null;
            ObjectOutputStream oos = null;

            try {
                fout = new FileOutputStream("src/GUIControllers/DBinventory.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(mainDB.getDB());
                fout = new FileOutputStream("src/GUIControllers/fleet.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(mainDB.getTotalInventory());
                fout = new FileOutputStream("src/GUIControllers/users.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(userList);


            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


    public static void main(String[] args) {
        launch(args);

    }


}
