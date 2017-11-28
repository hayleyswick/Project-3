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



    public void start(Stage primaryStage) throws Exception {


        /*
        ObjectInputStream objectinputstream = null;
        try {
            FileInputStream streamIn = new FileInputStream("src/GUIControllers/fleet.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            ArrayList<Warehouse> fleet = (ArrayList<Warehouse>) objectinputstream.readObject();
            mainDB.setFleet(fleet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                objectinputstream.close();
            }
        }
        */


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



        Inventory testInv1 = new Inventory(new BikePart("test1",123,10,5,false),5);
        Inventory testInv2 = new Inventory(new BikePart("test2",456,10,5,false),5);
        Inventory testInv3 = new Inventory(new BikePart("test3",789,10,5,true),5);


        System.out.println("Name: " + testInv1.getBikePart().getName() + ", ID: " + testInv1.getBikePart().getID() + ", Price: $"
                + testInv1.getBikePart().getPrice() + ", Sale Price: $" + testInv1.getBikePart().getSalePrice() + ", On Sale: "
                + testInv1.getBikePart().getIsOnSale()+", Quantity: "+testInv1.getQuantity()+".\n");



        mainDB.addInventory(testInv1);
        mainDB.addInventory(testInv2);
        mainDB.addInventory(testInv3);



        Parent root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        primaryStage.setTitle("User Login");
        primaryStage.setScene(new Scene(root, 325, 275));
        primaryStage.show();

        primaryStage.setOnCloseRequest(we -> {
            ObjectOutputStream oos = null;
            FileOutputStream fout = null;

            try {
                fout = new FileOutputStream("src/GUIControllers/fleet.ser");
                oos = new ObjectOutputStream(fout);
                oos.writeObject(mainDB.getTotalInventory());

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
