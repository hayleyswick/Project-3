package Users;

import Application.Inventory;
import Application.Warehouse;
import GUIControllers.Main;

import java.util.ArrayList;
import java.util.Iterator;

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

    public ArrayList<Inventory> generatePartsOrder(long quantity) {

        ArrayList<Inventory> parts = new ArrayList<>();
        long difference;

        for (Inventory i : Main.mainDB.getDB()) {
            if (i.getQuantity() < quantity) {
                difference = (quantity-i.getQuantity());
                parts.add(new Inventory(i.getBikePart(),difference));
            }

        }
        return parts;

    }
}
