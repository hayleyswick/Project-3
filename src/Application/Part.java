package Application;

/** Part interface that BikePart implements
 *
 */
public interface Part
{
    public String getName();
    public long getID();
    public double getPrice();
    public double getSalePrice();
    public boolean getIsOnSale();
    public String toString();
}