package application;

import java.util.ArrayList;

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
	

}
