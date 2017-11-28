package application;

import java.util.ArrayList;
import java.util.Iterator;

public class MainWareHouse extends Warehouse {  
	
	private ArrayList<Warehouse> fleet = new ArrayList<Warehouse>();    
      
	public void addWarehouse(Warehouse w){ 
		fleet.add(w); 
	}
	
	public ArrayList<Warehouse> getTotalInventory() {
		return fleet;
	}
	
	public void update(Inventory inventory){
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
	
	public ArrayList<Inventory> getAllParts() {
		ArrayList<Inventory> partList = new ArrayList<Inventory>();
		Iterator<Inventory> mainIterator = this.dbIterator();
		while (mainIterator.hasNext()) {
			Inventory i = mainIterator.next();
			partList.add(i);
		}
		for (Warehouse w : fleet) {
			Iterator<Inventory> it = w.dbIterator();
			while (it.hasNext()) {
				Inventory i = mainIterator.next();
				if (checkPartList(i, partList) != null){
					long[] idxAndQuantity = checkPartList(i, partList);
					int idx = (int) idxAndQuantity[0];
					long x = idxAndQuantity[1];
					Inventory I = modifyInventory(i, x);
					partList.set(idx, I);
				}
				else {
					partList.add(i);
				}
			}
		}
		return partList;
	}
	

}
