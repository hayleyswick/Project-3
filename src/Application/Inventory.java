package Application;

import java.io.Serializable;

public class Inventory extends BikePart implements Serializable {

    private BikePart bp;
    private long quantity;
    static final long serialVersionUID = 2;


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

    public String toString()
    {
        return bp.getName()+","+bp.getID()+","+bp.getCost()+","+bp.getSalePrice()+","+bp.getIsOnSale()+","+quantity+"\n";
    }

}