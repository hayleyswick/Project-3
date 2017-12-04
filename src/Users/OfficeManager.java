package Users;

import Application.Inventory;
import Application.Warehouse;
import GUIControllers.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import Application.*;

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

    public Inventory examinePartName(String name, ArrayList<Inventory> inv) {
        for (Inventory i : inv) {
            if (i.getBikePart().getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public Inventory examinePartID(Long ID, ArrayList<Inventory> inv) {
        for (Inventory i : inv) {
            if (i.getBikePart().getID() == ID) {
                return i;
            }
        }
        return null;
    }

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
                        " was paid $" + fmt.format(rawCheck*.15);

            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return check;
    }

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