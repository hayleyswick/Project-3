package Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Warehouse implements Serializable {

    static final long serialVersionUID = 23456;

    private ArrayList<Inventory> db = new ArrayList<>();

    public void addPart(BikePart bp, long x) {
        db.add(new Inventory(bp, x));
    }

    public void addInventory(Inventory inv) {
        db.add(inv);
    }

    public ArrayList<Inventory> getDB() {
        return db;
    }

    public void setDB(ArrayList<Inventory> db) {
        this.db = db;
    }

    public Iterator<Inventory> dbIterator() {
        return db.iterator();
    }

    public void updateInventory(Inventory inventory, long y, boolean change) {
        int index = 0;
        Iterator<Inventory> it = this.getDB().iterator();
        while (it.hasNext()) {
            if (it.next().equals(inventory)) {
                double p = inventory.getPrice();
                double s = inventory.getSalePrice();
                boolean iOS = inventory.getIsOnSale();
                long x = inventory.getQuantity();
                if (change == true)
                    inventory.updateBikePart(p, s, iOS, y);
                else
                    inventory.updateBikePart(p, s, iOS, x);
                this.getDB().set(index, inventory);
            }
            index++;
        }
    }




}
