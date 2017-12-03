package Application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SalesInvoice implements Serializable, Comparable<SalesInvoice>
{
    private String invoice = "";
    private String seller = "";
    private String bikeShopName = "";
    private Calendar date;
    private String dateFormated = "";
    private String signatureByPerson = "";
    private SimpleDateFormat dateFMT = new SimpleDateFormat("yyyy-MM-dd");
    private DecimalFormat fmt = new DecimalFormat("0.00");
    static final long serialVersionUID = 45678;
    private double totalSales;
    /**
     * Generates a sales invoice given an ArrayList of bike parts, the name of a bike shop a calendar object to show when the invoice was
     * created and who it was signed by
     * @param bp the BikePart ArrayList
     * @param bikeShop name of the bike shop the sales invoice is for
     * @param d the calendar object for the current date and time
     * @param signatureBy who the sale was signed by
     * @return the formatted invoice
     */
    public String generateSalesInvoice(ArrayList<Inventory> bp, String bikeShop, String signatureBy)
    {
        bikeShopName = bikeShop;
        date = Calendar.getInstance();
        dateFormated = dateFMT.format(new Date());
        signatureByPerson = signatureBy;
        for(int x=0;x<75;x++)
            invoice+="#";

        invoice += "\nSales Invoice for "+bikeShop+", "+date.getTime()+"\n";
        invoice+="Part Name \tPart Number \tPrice \tSales Price \tQnty \tTotal Cost\n";
        double[] total = new double[bp.size()];
        int x = 0;
        for(Inventory b : bp)
        {
            if(b.getBikePart().getIsOnSale())
                total[x] = b.getBikePart().getSalePrice()*b.getQuantity();
            else
                total[x] = b.getBikePart().getPrice()*b.getQuantity();
            invoice+=b.getBikePart().getName()+" \t"+b.getBikePart().getID()+" \t"+b.getBikePart().getCost()+" \t"+b.getBikePart().getSalePrice()
                    +" \t\t"+b.getQuantity()+" \t"+fmt.format(total[x])+"\n";
            x++;
        }
        double overallTotal = 0;
        for(Double t : total)
            overallTotal+=t;
        invoice+="Total:\t"+fmt.format(overallTotal)+"\n";
        totalSales = overallTotal;
        invoice+="\nSignature Recived By: "+signatureBy+"\n";
        for(int n=0;n<75;n++)
            invoice+="#";

        return invoice;
    }

    public String getDate()
    {
        return dateFormated;
    }
    public String getInvoice()
    {
        return invoice;
    }
    public String getBikeShopName()
    {
        return bikeShopName;
    }
    public void setDate(Calendar date)
    {
        this.date = date;
    }
    public String getSignature()
    {
        return signatureByPerson;
    }
    public void setSignature(String signatureByPerson)
    {
        this.signatureByPerson = signatureByPerson;
    }
    public double getTotalSales()
    {
        return totalSales;
    }

    public void setSeller(String s)
    {
        seller = s;
    }

    public String getSeller()
    {
        return seller;
    }

    public int compareTo(SalesInvoice arg0)
    {
        return this.getDate().compareTo(arg0.getDate());
    }

    public String toString()
    {
        return invoice;
    }
}
