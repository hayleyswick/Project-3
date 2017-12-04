package Application;

import GUIControllers.Main;

import java.util.ArrayList;
import java.util.Iterator;

/** Implements observer pattern: keeps track of itself and the sales van warehouses.
 * 
 * @author Hayley Swick
 *
 */
public class MainWareHouse extends Warehouse {

    private ArrayList<SalesVanWarehouse> fleet = new ArrayList<>();

    /** Adds a van to the fleet
     * 
     * @param w
     */
    public void addWarehouse(SalesVanWarehouse w) {
        fleet.add(w);
    }

    /** Returns the sales van fleet
     * 
     * @return
     */
    public ArrayList<SalesVanWarehouse> getFleet() {
        return fleet;
    }

    /** Sets the sales van fleet
     * 
     * @param fleet
     */
    public void setFleet(ArrayList<SalesVanWarehouse> fleet) {
        this.fleet = fleet;
    }

    /** Updates parts for all warehouses
     * 
     * @param inventory
     */
    public void update(Inventory inventory) {
        updateInventory(inventory, inventory.getQuantity(), false,Main.mainDB.getDB());
        for (Warehouse w : Main.mainDB.getFleet())
            w.updateInventory(inventory, inventory.getQuantity(), false,Main.mainDB.getDB());
    }


/** Returns a copy of the inventory object with a modified quantity
 * 
 * @param i
 * @param y
 * @return
 */
    public Inventory modifyInventory(Inventory i, long y) {
        return new Inventory(i.getBikePart(), i.getQuantity() + y);
    }

    /** Returns parts of all warehouses
     * 
     * @return
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
        return partList;
    }


}