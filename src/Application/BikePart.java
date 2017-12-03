package Application;

import java.io.Serializable;

/**
 * Stores information about bike parts:
 * name, number, list and sale prices, and
 * whether they are on sale.
 *
 * @author Hayley Swick
 */
public class BikePart implements Part, Cloneable, Serializable {
    private String name;
    private long ID;
    private double price;
    private double salePrice;
    private boolean isOnSale;
    static final long serialVersionUID = 1;


    /**
     * Default constructor
     *
     * @param
     * @return void
     */
    public BikePart() {

    }

    /**
     * Stores bike part information from a line of text.
     * Throws exception if line is not in proper format.
     *
     * @param String
     * @return void
     * @throws Exception
     */
    public BikePart(String n, long id, double p, double s, boolean iOS) {
        this.name = n;
        this.ID = id;
        this.price = p;
        this.salePrice = s;
        this.isOnSale = iOS;

    }

    /**
     * Returns the name of a bike part
     *
     * @param none
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of a bike part
     *
     * @param none
     * @return String
     */
    public long getID() {
        return ID;
    }


    /**
     * Returns the list price of a bike part
     *
     * @param none
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the list price of a bike part
     *
     * @param double
     * @return void
     */
    public void setPrice(double p) {
        this.price = p;
    }

    /**
     * Returns the sale price of a bike part
     *
     * @param none
     * @return double
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * Sets the sale price of a bike part
     *
     * @param double
     * @return void
     */
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * Returns whether a part is on sale.
     *
     * @param none
     * @return boolean
     */
    public boolean getIsOnSale() {
        return isOnSale;
    }

    /**
     * Sets whether a part is on sale
     *
     * @param String
     * @return void
     */
    public void setIsOnSale(boolean iOS) {
        this.isOnSale = iOS;
    }

    /**
     * Returns the cost of a bike part, taking
     * into account whether it is on sale.
     *
     * @param none
     * @return double
     */
    public double getCost() {
        if (isOnSale == true)
            return salePrice;
        else
            return price;
    }

    /**
     * Converts a BikePart into a String.
     *
     * @param none
     * @return String
     */
    public String toString() {
        String s = name + ",";
        s += Long.toString(ID) + ",";
        s += Double.toString(price) + ",";
        s += Double.toString(salePrice) + ",";
        s += Boolean.toString(isOnSale) + ",";
        return s;
    }

    /**
     * Clones a bike part
     *
     * @param none
     * @return BikePart
     */
    public BikePart getClone() {
        try {
            return (BikePart) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(" Cloning not allowed. ");
            return this;
        }
    }
}


//lines 133-141 from http://mrbool.com/how-to-implement-cloning-in-java-using-cloneable-interface/28410#ixzz4vmHvdpOW