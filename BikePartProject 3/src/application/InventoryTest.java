import static org.junit.Assert.*;

import org.junit.Test;

public class InventoryTest 
{

	@Test
	public void getBikePartTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		Inventory i = new Inventory(bp,4);
		assertEquals(bp,i.getBikePart());
	}
	
	@Test
	public void getQuantityTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		Inventory i = new Inventory(bp,4);
		i.setQuantity(5);
		int output = 5;
		assertEquals(output,i.getQuantity());
	}
	
	@Test
	public void updateBikePartTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		Inventory i = new Inventory(bp,4);
		BikePart outputBP = new BikePart("BikeSeat",1234567890,34,31,true);
		Inventory outputI = new Inventory(outputBP,3);
		i.updateBikePart(34, 31, true, 3);
		assertEquals(outputI.getBikePart().toString(),i.getBikePart().toString());
		assertEquals(outputI.getQuantity(),i.getQuantity());
	}
	
	@Test
	public void equalsFalseTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		Inventory i = new Inventory(bp,4);
		BikePart bp2 = new BikePart("BikeChain",1234567892,34,31,true);
		Inventory i2 = new Inventory(bp2,3);
		boolean output = false;
		assertEquals(output, i.equals(i2));
	}
	
	@Test
	public void equalsTrueTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		Inventory i = new Inventory(bp,4);
		BikePart bp2 = new BikePart("BikeSeat",1234567890,34,31,true);
		Inventory i2 = new Inventory(bp2,3);
		boolean output = true;
		assertEquals(output, i.equals(i2));
	}
}
