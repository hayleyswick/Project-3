package Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import Application.*;

public class SalesAssociate extends User {

	private ArrayList<SalesInvoice> invoices = new ArrayList<SalesInvoice>();
	private ArrayList<Inventory> inventory = new ArrayList<Inventory>();
	private SalesVanWarehouse s;
	static final long serialVersionUID = 8;
	
    public SalesAssociate(String username, String password, String email,
                    String firstName, String lastName, String phoneNumber) {
        super(username, password, email, firstName, lastName, phoneNumber);

    }
    public SalesAssociate(User user){
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());

    }
    
    public void addSalesVan(SalesVanWarehouse s)
    {
    		this.s = s;
    		inventory.addAll(s.getDB());
    }
    
    
    public void sellToBikeShop(String bikeShopName, String signatureBy, String fileOfPartsToSell) throws FileNotFoundException
	{
		ArrayList<Inventory>i = new ArrayList<Inventory>();
		File f = new File(fileOfPartsToSell);
		Scanner input = new Scanner(f);
		int num = input.nextInt();
		input.nextLine();
		for(int x=0;x<num;x++)
		{
			String line = input.nextLine();
			String[] elements = line.split(",");
			i.add(new Inventory(new BikePart(elements[0],Integer.parseInt(elements[1]),Double.parseDouble(elements[2]),Double.parseDouble(elements[3]),
					Boolean.parseBoolean(elements[4])),Integer.parseInt(elements[5])));
		}
		
		for(Inventory a : inventory)
			for(Inventory inv : i)
				if(a.getBikePart().getID()==inv.getBikePart().getID())
					a.setQuantity(a.getQuantity()-inv.getQuantity());
		
		SalesInvoice invoice = new SalesInvoice();
		invoice.setSeller(this.getFirstName()+" "+this.getLastName());
		invoice.generateSalesInvoice(i,bikeShopName,signatureBy);
		System.out.println(invoice.getInvoice());
		invoices.add(invoice);
		
	}
    
    public void loadVan(String file, MainWareHouse w)
    {
    		moveParts(file, w);
    		s.setDB(inventory);
    }
    
    private void moveParts(String file, MainWareHouse warehouse)
    {
    		ArrayList<Warehouse>w = new ArrayList<Warehouse>();
    		Warehouse main = w.get(0);
    		ArrayList<Inventory> inventoryW = new ArrayList<Inventory>();
    		inventoryW.addAll(main.getDB());
    		ArrayList<Inventory> inventoryS = new ArrayList<Inventory>();
    		inventoryS.addAll(s.getDB());
    		w.addAll(warehouse.getTotalInventory());
    		try
    		{
    			File f = new File(file);
    			Scanner input = new Scanner(f);
    			while(input.hasNextLine())
    			{
    				String line = input.nextLine();
    				String[] elements = line.split(",");
    				String partName = elements[0];
    				int quantity = Integer.parseInt(elements[1]);
    				for(Inventory i1 : inventoryW)
    				{
    					if(i1.getBikePart().getName().equals(partName))
						for(Inventory i2 : inventoryS)
						{
							Inventory temp = new Inventory(i1.getBikePart(),quantity);
							inventory.add(temp);
						}
    				}
    			}
    		}
    		catch(FileNotFoundException e)
    		{
    			System.out.println(e);
    		}
    }
	
	public ArrayList<SalesInvoice> getInvoices()
	{
		return invoices;
	}
	
	public void writeInvoices()
	{
		try 
		{
			Collections.sort(invoices);
			ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream("invoices.bin"));
			objOutputStream.writeObject(invoices);
		}   
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}
}
