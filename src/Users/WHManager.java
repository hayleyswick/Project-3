package Users;

import Application.Inventory;

import java.util.ArrayList;
import java.util.Iterator;

public class WHManager extends User {
    static final long serialVersionUID = 6;

    public WHManager(String username, String password, String email,
                     String firstName, String lastName, String phoneNumber) {
        super(username, password, email, firstName, lastName, phoneNumber);

    }

    public WHManager(User user) {
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
            if (i.getBikePart().getID()==ID) {
                return i;
            }
        }
        return null;
    }

    //public

}
