package Application;

import GUIControllers.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/** Template for warehouses; meant to be extended by MainWareHouse and SalesVanWarehouse
 * 
 * @author Hayley Swick
 *
 */
public abstract class Warehouse implements Serializable {

    static final long serialVersionUID = 23456;

    private ArrayList<Inventory> db = new ArrayList<>();

    /** Adds bike part to database based on bikepart and quantity as separate values
     * 
     * @param bp
     * @param x
     */
    public void addPart(BikePart bp, long x) {
        db.add(new Inventory(bp, x));
    }

    /** Adds bike part to database based on bikepart and quantity together in an inventory object
     * 
     * @param inv
     */
    public void addInventory(Inventory inv) {
        db.add(inv);
    }

    /** Returns the database
     * 
     * @return
     */
    public ArrayList<Inventory> getDB() {
        return db;
    }

    /** Sets the database
     * 
     * @param db
     */
    public void setDB(ArrayList<Inventory> db) {
        this.db = db;
    }

    /** Returns an iterator of the database
     * 
     * @return
     */
    public Iterator<Inventory> dbIterator() {
        return db.iterator();
    }

    /** Updates the bike part information for this warehouse
     * 
     * @param inventory
     * @param y
     * @param change
     * @param inv
     */
    public void updateInventory(Inventory inventory, long y, boolean change, ArrayList<Inventory> inv) {

        for (int i = 0;i<inv.size();i++) {

            if (inventory.getBikePart().getName().equals(inv.get(i).getBikePart().getName())) { //checks if it is in the list
                double p = inventory.getBikePart().getPrice(); //updates information
                double s = inventory.getBikePart().getSalePrice();
                boolean iOS = inventory.getBikePart().getIsOnSale();
                long x = inventory.getQuantity();
                if (change == true)
                    inventory.updateBikePart(p, s, iOS, y); //replaces quantity
                else
                    inventory.updateBikePart(p, s, iOS, x); //keeps quantity the same
                inv.set(i,inventory);

            }
        }
    }

    /** Checks to see if a part is in the list, returns the position in the list and quantity if it is
     * 
     * @param i
     * @param list
     * @return
     */
    public long[] checkPartList(Inventory i, ArrayList<Inventory> list) {
        long[] idxAndQuantity = new long[2];
        BikePart inventoryBP = i.getBikePart();
        String bpname = inventoryBP.getName();
        for (int idx = 0; idx < list.size(); idx++) {
            BikePart listBP = list.get(idx).getBikePart();
            if (bpname.equals(listBP.getName())) { //checks to see if part is in the list
                idxAndQuantity[0] = idx; //returns position
                idxAndQuantity[1] = list.get(idx).getQuantity(); //returns quantity
                return idxAndQuantity;
            }
        }
        return null;
    }


}