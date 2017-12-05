package Application;

import java.io.Serializable;

public class Inventory extends BikePart implements Serializable,Comparable<Inventory>{

    private BikePart bp;
    private long quantity;
    static final long serialVersionUID = 2;
    /**
     * Keeps track of the quantity for a BikePart
     *
     * @author Hayley Swick
     */


    public Inventory() {
    }

    public Inventory(BikePart part, long x) {
        this.bp = part;
        this.quantity = x;
    }

    public BikePart getBikePart() {
        return bp;
    }

    public void updateBikePart(double p, double s, boolean iOS, long x) {
        bp.setPrice(p);
        bp.setSalePrice(s);
        bp.setIsOnSale(iOS);
        this.quantity = x;

    }

    public boolean equals(Inventory other) {
        return getBikePart().getID() == other.getBikePart().getID();
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long x) {
        this.quantity = x;

    }

    /**
     *
     * @return Returns elements of Inventory class as a String separated by ,
     */

    public String toString()
    {
        return bp.getName()+","+bp.getID()+","+bp.getCost()+","+bp.getSalePrice()+","+bp.getIsOnSale()+","+quantity+"\n";
    }

    /**
     *
     * @return Returns a reoccurring string format used in the printing of an Inventory part to a TextArea.
     */

    public String appendTextFormat(){
        return ("Part Name: " + this.getBikePart().getName() + ", ID: " + this.getBikePart().getID() + ", Price: $"
                + this.getBikePart().getPrice() + ", Sale Price: $" + this.getBikePart().getSalePrice() + ", On Sale: "
                + this.getBikePart().getIsOnSale() + ", Quantity: " + this.getQuantity() + "\n");
    }

    @Override
    public int compareTo(Inventory o) {
        return Long.compare(this.getBikePart().getID(),o.getBikePart().getID());
    }
}