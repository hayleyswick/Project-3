package Application;

import GUIControllers.Main;

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

        for (int i = 0;i<Main.mainDB.getDB().size();i++) {

            if (inventory.getBikePart().getName().equals(Main.mainDB.getDB().get(i).getBikePart().getName())) {
                System.out.println(inventory.getName()+Main.mainDB.getDB().get(i).getName());
                double p = inventory.getBikePart().getPrice();
                double s = inventory.getBikePart().getSalePrice();
                boolean iOS = inventory.getBikePart().getIsOnSale();
                long x = inventory.getQuantity();
                if (change == true)
                    inventory.updateBikePart(p, s, iOS, y);
                else
                    inventory.updateBikePart(p, s, iOS, x);
                Main.mainDB.getDB().set(i,inventory);

            }
        }
    }


}
