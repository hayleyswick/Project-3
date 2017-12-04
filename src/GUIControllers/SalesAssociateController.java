package GUIControllers;

import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import Users.*;

public class SalesAssociateController
{
    private SalesAssociate currentUser = (SalesAssociate) Main.userList.get(Main.userIndex);
    public TextField textField;
    public TextArea textArea;
    public ObservableList<String> choices = FXCollections.observableArrayList("Sell to bike shop", "Load van");
    public ComboBox<String> choice;
    public Button button;

    public void setBox()
    {
        choice.setItems(choices);
    }

    public void setButton()
    {
    		if(choice.getValue().equals("Sell to bike shop"))
    			button.setText("Sell");
    		else
    			button.setText("Load Van");
    }
    
    public void Go()
    {
        switch(choice.getValue())
        {
            case "Sell to bike shop":
                try
                {
                    textArea.setText(currentUser.sellToBikeShop(textField.getText()));
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                break;
            case "Load van":
                currentUser.loadVan(textField.getText(), Main.mainDB);
        }
    }
}