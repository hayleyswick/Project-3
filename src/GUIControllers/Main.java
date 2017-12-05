package GUIControllers;

import Application.Inventory;
import Application.MainWareHouse;
import Application.SalesVanWarehouse;
import Users.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
        /*Set this boolean to true and delete the invoices.ser file in src/Files if you wish to start fresh.
        Otherwise, keep this on false to properly read in files at launch.
        */
        boolean setup = false;

        if (!setup) {
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


        if (setup) {
            SalesVanWarehouse van1 = new SalesVanWarehouse("Tim's Van");
            SalesVanWarehouse van2 = new SalesVanWarehouse("David's Van");
            SalesVanWarehouse van3 = new SalesVanWarehouse("Julia's Van");
            mainDB.generateInitialDatabase(van1,"timInventory.txt");
            mainDB.generateInitialDatabase(van2,"davidInventory.txt");
            mainDB.generateInitialDatabase(van3,"juliaInventory.txt");



            SysAdmin defaultAdmin = new SysAdmin("admin", "minda",
                    "admin@gmail.com", "John", "Roberts", "2515469442");
            OfficeManager testOfficeManager = new OfficeManager("bross", "test1",
                    "b.ross@gmail.com", "Betsy", "Ross", "6304468851");
            WHManager testWHManager = new WHManager("toast", "test2",
                    "j.norton@gmail.com", "James", "Norton", "2269062721");
            SalesAssociate testSalesAssociate = new SalesAssociate(new User("timps", "test3",
                    "t.simpson@gmail.com", "Tim", "Simpson", "1255464478"), van1);
            SalesAssociate testSalesAssociate2 = new SalesAssociate(new User("smithy", "test4",
                    "d.smith@gmail.com", "David", "Smith", "9495694371"), van2);
            SalesAssociate testSalesAssociate3 = new SalesAssociate(new User("jj", "test5",
                    "test@gmail.com", "Julia", "Johnson", "6719251352"), van3);

            mainDB.addWarehouse(testSalesAssociate.getS());
            mainDB.addWarehouse(testSalesAssociate2.getS());
            mainDB.addWarehouse(testSalesAssociate3.getS());

            mainDB.generateInitialDatabase(Main.mainDB,"initialInventory.txt");

            /*
            ArrayList<Inventory> loadVan = new ArrayList<>();
            Inventory temp = mainDB.getDB().get(0);
            temp.setQuantity(4);
            loadVan.add(temp);
            temp = mainDB.getDB().get(5);
            temp.setQuantity(3);
            loadVan.add(temp);
            temp = mainDB.getDB().get(2);
            temp.setQuantity(2);
            loadVan.add(temp);
            temp = mainDB.getDB().get(7);
            temp.setQuantity(3);
            loadVan.add(temp);
            temp = mainDB.getDB().get(9);
            temp.setQuantity(1);
            loadVan.add(temp);
            writer.writeToFile("loadVan",loadVan);
*/
            userList.add(defaultAdmin);
            userList.add(testOfficeManager);
            userList.add(testWHManager);
            userList.add(testSalesAssociate);
            userList.add(testSalesAssociate2);
            userList.add(testSalesAssociate3);


        }

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
