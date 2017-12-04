package Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Application.*;
import GUIControllers.Main;

public class SalesAssociate extends User {

    private ArrayList<SalesInvoice> invoices = new ArrayList<SalesInvoice>();
    private ArrayList<Inventory> inventory = new ArrayList<Inventory>();
    private SalesVanWarehouse s;
    static final long serialVersionUID = 8;

    public SalesAssociate(String username, String password, String email,
                          String firstName, String lastName, String phoneNumber) {
        super(username, password, email, firstName, lastName, phoneNumber);

    }

    public SalesAssociate(String username, String password, String email,
                          String firstName, String lastName, String phoneNumber, SalesVanWarehouse s) {
        super(username, password, email, firstName, lastName, phoneNumber);
        this.addSalesVan(s);

    }

    public SalesAssociate(User user) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());

    }

    public SalesAssociate(User user, SalesVanWarehouse s) {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setPhoneNumber(user.getPhoneNumber());
        this.addSalesVan(s);

    }

    public void addSalesVan(SalesVanWarehouse s) {
        this.s = s;
        inventory.addAll(s.getDB());
    }

    public SalesVanWarehouse getS() {
        return s;
    }

    public void setS(SalesVanWarehouse s) {
        this.s = s;
    }

    public String sellToBikeShop(String fileOfPartsToSell) throws FileNotFoundException {
        ArrayList<Inventory> i = new ArrayList<Inventory>();
        File f = new File("src/Files/" + fileOfPartsToSell);
        Scanner input = new Scanner(f);
        String shopAndOwner = input.nextLine();
        String bikeShopName = shopAndOwner.substring(0, shopAndOwner.indexOf(','));
        String signatureBy = shopAndOwner.substring(shopAndOwner.indexOf(',') + 1);
        int num = input.nextInt();
        input.nextLine();
        for (int x = 0; x < num; x++) {
            String line = input.nextLine();
            String[] elements = line.split(",");
            s.sellPart(Long.parseLong(elements[1]), Long.parseLong(elements[5]));
            i.add(new Inventory(new BikePart(elements[0], Integer.parseInt(elements[1]),
                    Double.parseDouble(elements[2]), Double.parseDouble(elements[3]), Boolean.parseBoolean(elements[4])), Long.parseLong(elements[5])));
        }


        SalesInvoice invoice = new SalesInvoice();
        invoice.setSeller(this.getUsername());
        invoice.generateSalesInvoice(i, bikeShopName, signatureBy);
        invoices.add(invoice);
        writeInvoices();
        return invoice.getInvoice();


    }


    public void moveParts(String filename) {

        ArrayList<Inventory> incomingInventory;
        long[] idxAndQuantity = new long[2];
        long[] mainQuantity = new long[2];
        long quantity;
        Inventory temp;

        File tempFile = new File(("src/Files/" + filename + ".ser"));

        if (tempFile.isFile()) {
            incomingInventory = (ArrayList<Inventory>) Main.writer.readFromFile(filename);
            for (Inventory i : incomingInventory) {

                idxAndQuantity = s.checkPartList(i, s.getDB());
                mainQuantity = Main.mainDB.checkPartList(i, Main.mainDB.getDB());

                if (idxAndQuantity == null) {
                    s.addInventory(i);
                } else {
                    temp = Main.mainDB.getDB().get((int) idxAndQuantity[0]);

                    quantity = (i.getQuantity() + idxAndQuantity[1]);
                    s.updateInventory(i, quantity, true, s.getDB());
                    Main.mainDB.updateInventory(temp, mainQuantity[1] - idxAndQuantity[1], true, Main.mainDB.getDB());

                }
            }
        }
    }


    public ArrayList<SalesInvoice> getInvoices() {
        return invoices;
    }

    public void writeInvoices() {
        File invoiceList = new File("src/Files/invoices.ser");
        ArrayList<SalesInvoice> totalInvoices = new ArrayList<SalesInvoice>();
        try {
            try {
                if (invoiceList.isFile()) {
                    ObjectInputStream objectinputstream = new ObjectInputStream(new FileInputStream("src/Files/invoices.ser"));
                    totalInvoices.addAll((ArrayList<SalesInvoice>) objectinputstream.readObject());
                }
                totalInvoices.addAll(invoices);
                Collections.sort(totalInvoices);
                ObjectOutputStream objOutputStream = new ObjectOutputStream(new FileOutputStream("src/Files/invoices.ser"));
                objOutputStream.writeObject(totalInvoices);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}