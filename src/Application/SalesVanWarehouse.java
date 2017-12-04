package Application;

import Users.SalesAssociate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SalesVanWarehouse extends Warehouse implements Serializable {


    private String name;
    static final long serialVersionUID = 34567;

    public SalesVanWarehouse(String n) {
        super();
        this.setName(n);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void movePart(Warehouse w, String partName, int quantity) {
        ArrayList<Inventory> wI = new ArrayList<Inventory>();
        wI.addAll(w.getDB());
        for (Inventory i1 : wI) {
            if (partName.equals(i1.getBikePart().getName())) {
                this.addPart(i1.getBikePart(), quantity);
                i1.setQuantity(i1.getQuantity() - quantity);
            }
        }
    }


    public void sellPart(long id, long x) {

        for (Inventory i : this.getDB()) {
            if (i.getBikePart().getID() == id) {
                long quantity = i.getQuantity();
                i.setQuantity(quantity - x);

            }

        }


    }
}