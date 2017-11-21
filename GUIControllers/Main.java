package GUIControllers;

import Users.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<User> userList = new ArrayList<>();

    public void start(Stage primaryStage) throws Exception{

        SysAdmin defaultAdmin = new SysAdmin("admin","minda",
                "test@gmail.com","joe","schmoe","7037328121");
        OfficeManager testOfficeManager = new OfficeManager("omanager","test1",
                "test@gmail.com","joe","schmoe","7037328121");
        WHManager testWHManager = new WHManager("whmanager","test2",
                "test@gmail.com","joe","schmoe","7037328121");
        SalesAssociate testSalesAssociate = new SalesAssociate("sassociate","test3",
                "test@gmail.com","joe","schmoe","7037328121");
        User testUser = new User("user","test4",
                "test@gmail.com","joe","schmoe","7037328121");

        userList.add(defaultAdmin);
        userList.add(testOfficeManager);
        userList.add(testWHManager);
        userList.add(testSalesAssociate);
        userList.add(testUser);


        Parent root = FXMLLoader.load(getClass().getResource("LoginController.fxml"));
        primaryStage.setTitle("User Login");
        primaryStage.setScene(new Scene(root, 325, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }


}
