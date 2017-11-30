package Application;

import Users.SalesAssociate;

import java.io.Serializable;
import java.util.Iterator;

public class SalesVanWarehouse extends Warehouse implements Serializable {
	

	private String name;
	private SalesAssociate operator;
	static final long serialVersionUID = 34567;
	
	public SalesVanWarehouse(String n, SalesAssociate operator) {
		this.setName(n);
		this.setOperator(operator);
	}

    public void setName(String name) {
        this.name = name;
    }

    public SalesAssociate getOperator() {
        return operator;
    }

    public void setOperator(SalesAssociate operator) {
        this.operator = operator;
    }

    public String getName() {
		return name;
	}
	
	
	public void sellPart(long id, long x) {
		Iterator<Inventory> it = dbIterator();
		while (it.hasNext()) {
			Inventory inventory = it.next();
			if (inventory.getID() == id) {
				long quantity = inventory.getQuantity();
				updateInventory(inventory, quantity-x, true);
			}
				
		}
		
	}

}
