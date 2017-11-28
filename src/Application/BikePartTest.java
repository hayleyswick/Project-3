package Application;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BikePartTest 
{
	@Test
	public void getNameTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		String output = "BikeSeat";
		assertEquals(output, bp.getName());
	}
	
	@Test
	public void getIDTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		long output = 1234567890;
		assertEquals(output, bp.getID());
	}
	
	@Test
	public void PriceTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		double output = 34.50;
		bp.setPrice(output);
		assertEquals(output, bp.getPrice(),.00);
	}
	
	@Test
	public void salesPriceTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		double output = 32.50;
		bp.setSalePrice(output);
		assertEquals(output, bp.getSalePrice(),.00);
	}

	@Test
	public void isOnSaleTest()
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		boolean output = true;
		bp.setIsOnSale(output);
		assertEquals(output,bp.getIsOnSale());
	}
	
	@Test
	public void getCostTestForTrue()
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,true);
		double output = 31.23;
		assertEquals(output,bp.getCost(),.00);
	}
	
	@Test
	public void getCostTestForFalse()
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		double output = 35.86;
		assertEquals(output,bp.getCost(),.00);
	}
	
	@Test
	public void toStringTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		String output = "BikeSeat,1234567890,35.86,31.23,false,";
		assertEquals(output, bp.toString());
	}
	
	@Test
	public void getCloneTest() 
	{
		BikePart bp = new BikePart("BikeSeat",1234567890,35.86,31.23,false);
		BikePart b = bp.getClone();
		String output = "BikeSeat,1234567890,35.86,31.23,false,";
		assertEquals(output, b.toString());
	}
}
