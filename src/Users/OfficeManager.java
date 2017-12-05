package Users;

import Application.Inventory;
import Application.SalesInvoice;
import GUIControllers.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A type of user that can search for a part by name or ID, generate commission for a Sales Associate, and create a resupply order.
 *
 * @author Liam Caudill
 */

public class OfficeManager extends User {
    static final long serialVersionUID = 9;

    public OfficeManager(String username, String password, String email,
                         String firstName, String lastName, String phoneNumber) {
        super(username, password, email, firstName, lastName, phoneNumber);

    }

    public OfficeManager(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());
    }

    /**
     * @param name Name of part that is being looking for.
     * @param inv  Inventory in which to look for the part.
     * @return The Inventory being searched for if it exists in the given ArrayList<Inventory>, or null if it doesn't
     */

    public Inventory examinePartName(String name, ArrayList<Inventory> inv) {
        for (Inventory i : inv) {
            if (i.getBikePart().getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    /**
     * @param ID  ID of part that is being looking for.
     * @param inv Inventory in which to look for the part.
     * @return The Inventory being searched for if it exists in the given ArrayList<Inventory>, or null if it doesn't
     */

    public Inventory examinePartID(Long ID, ArrayList<Inventory> inv) {
        for (Inventory i : inv) {
            if (i.getBikePart().getID() == ID) {
                return i;
            }
        }
        return null;
    }

    /**
     *
     * @param username Username of the Sales Associate to generate commission for.
     * @param startDate Starting date for the range of invoices to check.
     * @param endDate Ending date for the range of invoices to check.
     * @return A String sentence denoting how much the Associate has earned.
     * @author Will Jones
     */
    public String paySalesAssociate(String username, Date startDate, Date endDate) {
        String check = "";
        ArrayList<SalesInvoice> invoices = new ArrayList<SalesInvoice>();
        try {
            ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream("src/Files/invoices.ser"));
            try {
                invoices.addAll((ArrayList<SalesInvoice>) objInputStream.readObject());
                ArrayList<SalesInvoice> userInvoices = new ArrayList<SalesInvoice>();
                for (SalesInvoice i : invoices) {
                    if (i.getSeller().equals(username))
                        userInvoices.add(i);
                }
                ArrayList<SalesInvoice> valid = new ArrayList<SalesInvoice>();
                for (SalesInvoice i : invoices) {
                    if (i.getDate().after(startDate) && i.getDate().before(endDate)) {
                        valid.add(i);
                    }
                }
                double rawCheck = 0;
                for (SalesInvoice i : valid) {
                    rawCheck += i.getTotalSales();
                }
                DecimalFormat fmt = new DecimalFormat("0.00");
                check = username +
                        " has earned $" + fmt.format(rawCheck * .15);

            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return check;
    }

    /**
     *
     * @param quantity The minimum quantity of part required.
     * @return An ArrayList containing all the parts from the Main Warehouse that have a quantity lower than
     * 10, with the given quantity being the difference between the current and the entered.
     */
    public ArrayList<Inventory> generatePartsOrder(long quantity) {

        ArrayList<Inventory> parts = new ArrayList<>();
        long difference;

        for (Inventory i : Main.mainDB.getDB()) {
            if (i.getQuantity() < quantity) {
                difference = (quantity - i.getQuantity());
                parts.add(new Inventory(i.getBikePart(), difference));
            }

        }
        return parts;

    }
}