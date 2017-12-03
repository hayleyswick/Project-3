package Application;

import GUIControllers.Main;

import java.util.ArrayList;
import java.util.Iterator;

public class MainWareHouse extends Warehouse {

    private ArrayList<SalesVanWarehouse> fleet = new ArrayList<>();

    public void addWarehouse(SalesVanWarehouse w) {
        fleet.add(w);
    }

    public ArrayList<SalesVanWarehouse> getFleet() {
        return fleet;
    }

    public void setFleet(ArrayList<SalesVanWarehouse> fleet) {
        this.fleet = fleet;
    }

    public void update(Inventory inventory) {
        updateInventory(inventory, inventory.getQuantity(), false);
        for (Warehouse w : fleet)
            w.updateInventory(inventory, inventory.getQuantity(), false);
    }

    public long[] checkPartList(Inventory i, ArrayList<Inventory> list) {
        long[] idxAndQuantity = new long[2];
        BikePart inventoryBP = i.getBikePart();
        for (int idx = 0; idx < list.size(); idx++) {
            BikePart listBP = list.get(idx).getBikePart();
            if (inventoryBP.equals(listBP)) {
                idxAndQuantity[0] = idx;
                idxAndQuantity[1] = list.get(idx).getQuantity();
                return idxAndQuantity;
            }
        }
        return null;
    }

    public Inventory modifyInventory(Inventory i, long y) {
        return new Inventory(i.getBikePart(), i.getQuantity() + y);
    }

    public ArrayList<SalesVanWarehouse> getAllInventories() {
        ArrayList<SalesVanWarehouse> partList = new ArrayList<>();

        SalesVanWarehouse maindb = new SalesVanWarehouse("Main Database");

        for (Inventory i : Main.mainDB.getDB()) {
            maindb.addInventory(i);
        }
        partList.add(maindb);

        for (SalesVanWarehouse w : Main.mainDB.getFleet()) {
            partList.add(w);
        }
        return partList;
    }


}
