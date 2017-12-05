package Application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SalesInvoice implements Serializable, Comparable<SalesInvoice> {
    private String invoice = "";
    private String seller = "";
    private String bikeShopName = "";
    private Calendar date;
    private String signatureByPerson = "";
    private Calendar d;
    private DecimalFormat fmt = new DecimalFormat("0.00");
    static final long serialVersionUID = 45678;
    private double totalSales;

    /**
     * Generates a sales invoice given an ArrayList of bike parts, the name of a bike shop a calendar object to show when the invoice was
     * created and who it was signed by
     *
     * @param bp          the BikePart ArrayList
     * @param bikeShop    name of the bike shop the sales invoice is for
     * @param signatureBy who the sale was signed by
     * @return the formatted invoice
     */
    public String generateSalesInvoice(ArrayList<Inventory> bp, String bikeShop, String signatureBy) {
        bikeShopName = bikeShop;
        d = Calendar.getInstance();
        signatureByPerson = signatureBy;
        invoice += "\n";
        for (int x = 0; x < 90; x++)
            invoice += "#";

        invoice += "\nSales Invoice for " + bikeShop + ", " + d.getTime() + "\n";
        invoice += String.format("%01$-25s%02$-25s%03$-25s%04$-25s%05$-25s%06$-25s",
                "Part Name", "Part Number", "Normal Price", "Sales Price", "Quantity", "Total Cost");
        invoice += "\n";
        double[] total = new double[bp.size()];
        int x = 0;
        for (Inventory b : bp) {
            if (b.getBikePart().getIsOnSale())
                total[x] = b.getBikePart().getSalePrice() * b.getQuantity();
            else
                total[x] = b.getBikePart().getPrice() * b.getQuantity();

            String temp = String.format("%01$-25s%02$-25d%03$-25f%04$-25f%05$-25d%06$-25s",
                    b.getBikePart().getName(),
                    b.getBikePart().getID(),
                    b.getBikePart().getPrice(), b.getBikePart().getCost(),
                    b.getQuantity(), fmt.format(total[x]));
            invoice += temp;
            invoice += "\n";

            x++;
        }
        double overallTotal = 0;
        for (Double t : total)
            overallTotal += t;
        invoice += "Total: \t" + "$" + fmt.format(overallTotal) + "\n";
        totalSales = overallTotal;
        invoice += "\nSignature Received By: " + signatureBy + "\n";
        for (int n = 0; n < 90; n++)
            invoice += "#";

        return invoice + "\n";
    }

    public Date getDate() {
        return d.getTime();
    }

    public String getInvoice() {
        return invoice;
    }

    public String getBikeShopName() {
        return bikeShopName;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getSignature() {
        return signatureByPerson;
    }

    public void setSignature(String signatureByPerson) {
        this.signatureByPerson = signatureByPerson;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setSeller(String s) {
        seller = s;
    }

    public String getSeller() {
        return seller;
    }

    public int compareTo(SalesInvoice arg0) {
        return this.getDate().compareTo(arg0.getDate());
    }

    public String toString() {
        return invoice;
    }


}