package Application;

import GUIControllers.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements observer pattern: keeps track of itself and the sales van warehouses.
 *
 * @author Hayley Swick
 */
public class MainWareHouse extends Warehouse {

    private ArrayList<SalesVanWarehouse> fleet = new ArrayList<>();

    /**
     * Adds a van to the fleet
     *
     * @param w
     */
    public void addWarehouse(SalesVanWarehouse w) {
        fleet.add(w);
    }

    /**
     * Returns the sales van fleet
     *
     * @return
     */
    public ArrayList<SalesVanWarehouse> getFleet() {
        return fleet;
    }

    /**
     * Sets the sales van fleet
     *
     * @param fleet
     */
    public void setFleet(ArrayList<SalesVanWarehouse> fleet) {
        this.fleet = fleet;
    }

    /**
     * Updates parts for all warehouses
     *
     * @param inventory Part with new properties
     */
    public void update(Inventory inventory) {
        updateInventory(inventory, inventory.getQuantity(), false, Main.mainDB.getDB());
        for (Warehouse w : Main.mainDB.getFleet())
            w.updateInventory(inventory, inventory.getQuantity(), false, Main.mainDB.getDB());
    }


    /**
     * Returns a copy of the inventory object with a modified quantity
     *
     * @param i Inventory object to change quantity to
     * @param y Quantity to set for Inventory object
     * @return Modified Inventory object
     */
    public Inventory modifyInventory(Inventory i, long y) {
        return new Inventory(i.getBikePart(), i.getQuantity() + y);
    }

    /**
     * Returns parts of the Main Warehouse and all Sales Vans
     *
     * @return ArrayList<SalesVan> Contains entire inventory, with the MainDB also being stored in a SalesVan.
     */
    public ArrayList<SalesVanWarehouse> getAllInventories() {
        ArrayList<SalesVanWarehouse> partList = new ArrayList<>();

        SalesVanWarehouse maindb = new SalesVanWarehouse("Main Database");

        for (Inventory i : Main.mainDB.getDB()) {
            maindb.addInventory(i);
        }
        partList.add(maindb); //adds main warehouse parts

        for (SalesVanWarehouse w : Main.mainDB.getFleet()) {
            partList.add(w); //adds parts from each sales van
        }
        /*
        for(User u: Main.userList){
            if (u.getClass().toString().equals("class Users.SalesAssociate")){

            }
        }
        */
        return partList;
    }

    /**
     * Generates an initial database for the main warehouse by reading in a .txt file.
     *
     * @param w Warehouse to have inventory read into
     * @param filename Name of file that parts are being read in from.
     * @throws IOException Thrown if file does not exist.
     */
    public void generateInitialDatabase(Warehouse w, String filename) throws IOException {

        FileReader fr = new FileReader("src/Files/"+filename);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            String[] elements = line.split(",");
            Inventory temp = new Inventory((new BikePart(elements[0], Long.parseLong(elements[1]),
                    Double.parseDouble(elements[2]), Double.parseDouble(elements[3]),
                    Boolean.parseBoolean(elements[4]))),Long.parseLong(elements[5]));

            w.getDB().add(temp);
        }
        br.close();
    }


}