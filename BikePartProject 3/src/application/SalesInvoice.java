import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class SalesInvoice implements Serializable
{

  /**
	 * Generates a sales invoice given an ArrayList of bike parts, the name of a bike shop a calendar object to show when the invoice was
	 * created and who it was signed by
	 * @param bp the BikePart ArrayList
	 * @param bikeShop name of the bike shop the sales invoice is for
	 * @param d the calendar object for the current date and time
	 * @param signatureBy who the sale was signed by
	 * @return the formatted invoice
	 */
	public String generateSalesInvoice(ArrayList<BikePart> bp, String bikeShop, Calendar d, String signatureBy)
	{
		String invoice = "";
		for(int x=0;x<75;x++)
			invoice+="#";
		
		invoice += "\nSales Invoice for "+bikeShop+", "+d.getTime()+"\n";
		invoice+="Part Name \tPart Number \tPrice \tSales Price \tQnty \tTotal Cost\n";
		double[] total = new double[bp.size()];
		int x = 0;
		for(BikePart b : bp)
		{
			if(b.isOnSale())
				total[x] = b.getSalePrice()*b.getQuantity();
			else
				total[x] = b.getPrice()*b.getQuantity();
			invoice+=b.getPartName()+" \t"+b.getPartNum()+" \t"+b.getPrice()+" \t"+b.getSalePrice()+" \t"+b.getQuantity()+" \t"+total[x]+"\n";
			
		}
		double overallTotal = 0;
		for(Double t : total)
			overallTotal+=t;
		invoice+="Total:\t"+overallTotal+"\n";
		invoice+="\nSignature Recived By: "+signatureBy+"\n";
		for(int n=0;n<75;n++)
			invoice+="#";
		
		return invoice;
	}
}
