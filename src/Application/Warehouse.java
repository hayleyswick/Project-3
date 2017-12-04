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

    public void updateInventory(Inventory inventory, long y, boolean change, ArrayList<Inventory> inv) {

        for (int i = 0;i<inv.size();i++) {

            if (inventory.getBikePart().getName().equals(inv.get(i).getBikePart().getName())) {
                double p = inventory.getBikePart().getPrice();
                double s = inventory.getBikePart().getSalePrice();
                boolean iOS = inventory.getBikePart().getIsOnSale();
                long x = inventory.getQuantity();
                if (change == true)
                    inventory.updateBikePart(p, s, iOS, y);
                else
                    inventory.updateBikePart(p, s, iOS, x);
                inv.set(i,inventory);

            }
        }
    }

    public long[] checkPartList(Inventory i, ArrayList<Inventory> list) {
        long[] idxAndQuantity = new long[2];
        BikePart inventoryBP = i.getBikePart();
        String bpname = inventoryBP.getName();
        for (int idx = 0; idx < list.size(); idx++) {
            BikePart listBP = list.get(idx).getBikePart();
            if (bpname.equals(listBP.getName())) {
                idxAndQuantity[0] = idx;
                idxAndQuantity[1] = list.get(idx).getQuantity();
                return idxAndQuantity;
            }
        }
        return null;
    }


}
