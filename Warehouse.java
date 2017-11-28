package application;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Warehouse {
	
	private ArrayList<Inventory> db = new ArrayList<Inventory>();
	
	public void addPart(BikePart bp, long x) {
		db.add(new Inventory(bp, x));
	}
	
	public Iterator<Inventory> dbIterator(){
		return db.iterator();
	}
	
	public void updateInventory(Inventory inventory, long y, boolean change) {
		int index = 0;
		Iterator<Inventory> it = db.iterator();
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
				db.set(index, inventory);
			}
			index++;
		}
	}
	
	public Inventory examinePart(String name) {
		Iterator<Inventory> it = db.iterator();
		while (it.hasNext()) {
			Inventory i = it.next();
			if (i.getName().equals(name)) 
				return i;
		}
		return null;
	}

}
