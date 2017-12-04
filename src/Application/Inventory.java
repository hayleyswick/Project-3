package Application;

import java.io.Serializable;

/** Keeps track of quantity of bike parts
 * 
 * @author Hayley Swick
 *
 */
public class Inventory extends BikePart implements Serializable {

    private BikePart bp;
    private long quantity;
    static final long serialVersionUID = 2;

/** Default constructor
 * 
 */
    public Inventory() {
    }

    /** Constructs new Inventory based on bike part information and quantity
     * 
     * @param part
     * @param x
     */
    public Inventory(BikePart part, long x) {
        this.bp = part;
        this.quantity = x;
    }

    /** Returns bike part information of Inventory
     * 
     * @return
     */
    public BikePart getBikePart() {
        return bp;
    }

    /** Updates bike part in Inventory
     * 
     * @param p
     * @param s
     * @param iOS
     * @param x
     */
    public void updateBikePart(double p, double s, boolean iOS, long x) {
        bp.setPrice(p);
        bp.setSalePrice(s);
        bp.setIsOnSale(iOS);
        this.quantity = x;

    }

    /** Checks to see if parts in inventory are equal
     * 
     * @param other
     * @return
     */
    public boolean equals(Inventory other) {
        return getBikePart().getID() == other.getBikePart().getID();
    }

    /** Returns quantity of bike parts
     * 
     * @return
     */
    public long getQuantity() {
        return quantity;
    }

    /** Updates quantity of bike parts
     * 
     * @param x
     */
    public void setQuantity(long x) {
        this.quantity = x;

    }

    /** Converts inventory object to a string
     * 
     */
    public String toString()
    {
        return bp.getName()+","+bp.getID()+","+bp.getCost()+","+bp.getSalePrice()+","+bp.getIsOnSale()+","+quantity+"\n";
    }

}