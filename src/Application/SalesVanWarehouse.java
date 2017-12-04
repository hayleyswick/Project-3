package Application;

import Users.SalesAssociate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/** Warehouse used for selling bike parts
 * 
 * @author Hayley Swick
 *
 */
public class SalesVanWarehouse extends Warehouse implements Serializable {


    private String name;
    static final long serialVersionUID = 34567;

    /** Constructor based on name
     * 
     * @param n
     */
    public SalesVanWarehouse(String n) {
        super();
        this.setName(n);
    }

    /** Sets the name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Returns the name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /** Moves parts between warehouses
     * 
     * @param w
     * @param partName
     * @param quantity
     */
    public void movePart(Warehouse w, String partName, int quantity) {
        ArrayList<Inventory> wI = new ArrayList<Inventory>();
        wI.addAll(w.getDB()); //creates copy of database of w
        for (Inventory i1 : wI) {
            if (partName.equals(i1.getBikePart().getName())) { 
                this.addPart(i1.getBikePart(), quantity); //moves parts
                i1.setQuantity(i1.getQuantity() - quantity); //decrements quantity
            }
        }
    }


    /** Sells a part by number
     * 
     * @param id
     * @param x
     */
    public void sellPart(long id, long x) {

        for (Inventory i : this.getDB()) {
            if (i.getBikePart().getID() == id) { //checks if part exists
                long quantity = i.getQuantity();
                i.setQuantity(quantity - x); //decrements quantity

            }

        }


    }
}